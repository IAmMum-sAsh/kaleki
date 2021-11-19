
import React, {Component} from 'react';
import './accept_into_company.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function createProject(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/accept_into_company', {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + a,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())

}

class AcceptIntoCompany extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _user_id: 0,
            _company_id: 0,
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

        let user_id = Number(this.state._user_id);
        let company_id = Number(this.state._company_id);

        console.log(user_id + ' ' + company_id);

        const req = await createProject({
            user_id, company_id
        },);
        const cookies = new Cookies();
        cookies.set('req', req, {path: '/projects'});

        // window.location.reload();
    }


    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <h1>Создать проект</h1>
                <div className="form" noValidate>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" placeholder=" "
                            id="user_id"
                            label="ID пользователя"
                            name="_user_id"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="name" className="placeholder">ID пользователя</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" placeholder=" "
                            id="company_id"
                            label="ID компании"
                            name="_company_id"
                            onChange={this.handleInputChange}
                            autocomplete="off"
                        />
                        <div className="cut"></div>
                        <label htmlFor="company_id" className="placeholder">ID компании</label>
                    </div>
                    <button
                        type="submit"
                        onClick={this.handleSubmit}
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="submit"
                    >
                        Принять
                    </button>
                </div>
            </div>
        );
    }
}

export default AcceptIntoCompany;