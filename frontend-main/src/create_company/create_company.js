
import React, {Component} from 'react';
import './create_company.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function writeOff(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/create_company/', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + a,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())

}

class CreateCompany extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _name: '',
            _address: '',
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

        let name = this.state._name;
        let address = this.state._address

        const req = await writeOff({
            name, address
        },);
        const cookies = new Cookies();
        cookies.set('req', req, {path: '/companies'});

        this.props.history.push('/companies');
        window.location.reload();
    }


    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <h1>Создать компанию</h1>
                <div className="form" noValidate>
                    <div className="input-container ic1">
                        <input
                            className="input" type="text" placeholder=" "
                            id="name"
                            label="Название"
                            name="_name"
                            onChange={this.handleInputChange}
                        />
                        <div className="cut"></div>
                        <label htmlFor="hours" className="placeholder">Название</label>
                    </div>
                    <div className="input-container ic1">
                        <input
                            className="input" type="text" placeholder=" "
                            id="address"
                            label="Адрес"
                            name="_address"
                            onChange={this.handleInputChange}
                        />
                        <div className="cut"></div>
                        <label htmlFor="hours" className="placeholder">Адрес</label>
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

export default CreateCompany;