
import React, {Component} from 'react';
import './my_projects_by_id.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function writeOff(credentials, project_id) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/my_projects/'+ Number(project_id) + '/write_off', {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + a,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json());

}

const STYLE = {
    worn: {
        display: 'none'
    }
};

class MyProjectById extends Component {
    constructor(props) {
        super(props);
        this.state = {
            page_id: 0,
            projects: [],
            _hours: 0,
            worn: '',
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        };

        // //const [token, setToken] = useState();
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }


    handleSubmit = async e => {
        e.preventDefault();

        let hours = this.state._hours;

        const req = await writeOff({
            hours
        }, this.state.page_id);
        const cookies = new Cookies();
        // cookies.set('hours', req.hours, {path: ('/my_projects/' + this.state.page_id)});

        console.log(req);

        if (req.id < 0){
            this.setState({
                ['warn']: req.name
            });
            STYLE.worn = {display: 'block'};
        } else{
            // this.props.history.push(('/my_projects/' + this.state.page_id));
            STYLE.worn = {display: 'none'};
            window.location.reload();
        }

    }



    async getMyProjectById() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        let project_id =document.URL;

        let ind = project_id.lastIndexOf('my_projects/');

        project_id = project_id.slice(ind+12);
        const name = 'page_id';

        this.setState({
            [name]: project_id
        });

        return await fetch('/api/my_projects/'+Number(project_id), {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getMyProjectById();
        this.setState({projects: prs});
    }

    renderProjects() {
        let projects = this.state.projects;

        if (projects == null) {return;}

        let projectCards = [];
        let clnm = "";
        if (projects.status == "FROZEN") {clnm = "bluest";}
        else if (projects.status == "ACTIVE") {clnm = "greenst";}
        let salary = projects.base_salary * projects.rate;
        let max_hours = 40 * projects.rate;
        projectCards.push(
            <div className="card">
                <span className="comp-name"><span className={"dot " + clnm}/>{projects.name}</span>

                <input type="checkbox" className="close-form" onClick={event => {}}/>
                <div className="card-body">
                    <h2>Должность: {projects.position}</h2>
                    <h2>Ставка: {projects.rate}</h2>
                    <h2>Базовая заработная плата: {salary}р.</h2>
                    <br />
                    <progress  max={max_hours} value={projects.week_work_time}/>
                    <h5>{projects.week_work_time} из {max_hours} часов</h5>
                    <br />
                    <p>Менеджер проекта: <b>{projects.ceo_username}</b>.
                        Вы можете связаться с ним по всем, интересующим вас вопросам по электронной почте <a className="hvr" href={"mailto:"+projects.ceo_email}>{projects.ceo_email}</a></p>
                    <h5>Компания: {projects.company_name}</h5>
                    <h5>Дата начала: {projects.start_date}</h5>
                </div>
            </div>
        );
        projectCards.push(
            <div className="form" noValidate>
                <div className="input-container ic1">
                    <input
                        className="input" type="number" min="1" placeholder=" "
                        id="hours"
                        label="Часы"
                        name="_hours"
                        onChange={this.handleInputChange}
                        autocomplete="off"
                    />
                    <div className="cut"></div>
                    <label htmlFor="hours" className="placeholder">Часы</label>
                </div>
                <button
                    type="submit"
                    onClick={this.handleSubmit}
                    fullWidth
                    variant="contained"
                    color="primary"
                    className="submit"
                >
                    Списать
                </button>
            </div>
        );

        return projectCards;
    }

    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <div className="worn" style={STYLE.worn}>
                    <span id="error_text">{this.state.warn}</span>
                </div>

                <h1>Списать часы</h1>
                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderProjects()}
                    </div>
                </div>
            </div>
        );
    }
}

export default MyProjectById;