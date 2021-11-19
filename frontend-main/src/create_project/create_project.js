
import React, {Component} from 'react';
import './create_project.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function createProject(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/create_project/', {
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

class CreateProject extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _name: '',
            _company: 0,
            _start_date: '',
            _status: 'ACTIVE',
            warn: '',
            _salary: 0,
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

    handleChange = (event) => {
        const value = event.target.value;
        const name = '_status';
        this.setState({
            [name]: value
        });
    };


    handleSubmit = async e => {
        e.preventDefault();

        let name = this.state._name;
        let company = this.state._company;
        let start_date = this.state._start_date;
        let status = this.state._status;
        let salary = this.state._salary;

        const req = await createProject({
            name, company, start_date, status, salary
        },);
        const cookies = new Cookies();
        cookies.set('req', req, {path: '/projects'});

        if (req.id < 0){
            this.setState({
                ['warn']: req.name
            });
            STYLE.worn = {display: 'block'};
        } else{
            this.props.history.push('/projects');
            STYLE.worn = {display: 'none'};
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

                <div className="help">
                    <p>Формат даты: гггг-мм-дд</p>
                </div>

                <h1>Создать проект</h1>
                <div className="form" noValidate>
                    <div className="input-container ic1">
                        <input
                            className="input" type="text" placeholder=" "
                            id="name"
                            label="Название"
                            name="_name"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="name" className="placeholder">Название</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" placeholder=" "
                            id="company"
                            label="ID компании"
                            name="_company"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="company_id" className="placeholder">ID компании</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="text" placeholder=" "
                            id="start_date"
                            label="Дата старта"
                            name="_start_date"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="start_date" className="placeholder">Дата старта</label>
                    </div>
                    <div className="input-container ic1">
                        <select className="input" name="_status"
                                labelId="demo-simple-select-label"
                                id="status"
                                value={this.state._status}
                                label="Статус"
                                onChange={this.handleChange}
                        >
                            <option value={'ACTIVE'}>ACTIVE</option>
                            <option value={'FROZEN'}>FROZEN</option>
                        </select>
                        <div className="cut"></div>
                        <label htmlFor="status" className="placeholder">Статус</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="text" placeholder=" "
                            id="salary"
                            label="ЗП"
                            name="_salary"
                            onChange={this.handleInputChange}
                            autoComplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="salary" className="placeholder">ЗП</label>
                    </div>
                    <button
                        type="submit"
                        onClick={this.handleSubmit}
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="submit"
                    >
                        Создать
                    </button>
                </div>
            </div>
        );
    }
}

export default CreateProject;