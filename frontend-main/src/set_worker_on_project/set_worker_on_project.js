
import React, {Component} from 'react';
import './set_worker_on_project.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";
import HelpComponent from "../help_position_component/help_component";

async function setWorkerOnProject(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/set_worker_on_project/', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + a,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())

}

const STYLE = {
    worn: {
        display: 'none'
    }
};

class SetWorkerOnProject extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _user_id: 0,
            _project_id: 0,
            _position_id: 0,
            _rate: 0,
            _base_salary: 0,
            _week_work_time: 0,
            warn: '',
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

        let user_id = this.state._user_id;
        let project_id = this.state._project_id;
        let position_id = this.state._position_id;
        let rate = this.state._rate;
        let base_salary = this.state._base_salary;
        let week_work_time = this.state._week_work_time;

        const req = await setWorkerOnProject({
            user_id, project_id, position_id, rate, base_salary, week_work_time
        },);
        const cookies = new Cookies();
        cookies.set('req', req, {path: '/projects'});

        if (req.rate < 0){
            this.setState({
                ['warn']: req.position
            });
            STYLE.worn = {display: 'block'};
        } else{
            STYLE.worn = {display: 'none'};
            window.location.reload();
        }
    }


    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <div className="worn" style={STYLE.worn}>
                    <span id="error_text">{this.state.warn}</span>
                </div>

                <HelpComponent />

                <h1>Поставить работника на проект</h1>
                <div className="form" noValidate>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" min="1" placeholder=" "
                            id="user_id"
                            label="ID работника"
                            name="_user_id"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="name" className="placeholder">ID работника</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" min="1" placeholder=" "
                            id="project_id"
                            label="ID проекта"
                            name="_project_id"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="company_id" className="placeholder">ID проекта</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" min="1" placeholder=" "
                            id="position_id"
                            label="Должность"
                            name="_position_id"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="start_date" className="placeholder">Должность</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" step="0.1" placeholder=" "
                            id="rate"
                            label="Ставка"
                            name="_rate"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="hours" className="placeholder">Ставка</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" step="1000" placeholder=" "
                            id="base_salary"
                            label="Базовая заработная плата"
                            name="_base_salary"
                            onChange={this.handleInputChange}
                            autoComplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="hours" className="placeholder">Базовая заработная плата</label>
                    </div>
                    <button
                        type="submit"
                        onClick={this.handleSubmit}
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="submit"
                    >
                        Поставить
                    </button>
                </div>
            </div>
        );
    }
}

export default SetWorkerOnProject;