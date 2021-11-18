
import React, {Component} from 'react';
import './change_project_status.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function changeProjectStatus(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');

    return fetch('/api/change_project_status/', {
        method: 'PUT',
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

class ChangeProjectStatus extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _project_id: 0,
            _status: 'ACTIVE',
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

    handleChange = (event) => {
        const value = event.target.value;
        const name = '_status';
        this.setState({
            [name]: value
        });
    };


    handleSubmit = async e => {
        e.preventDefault();

        let project_id = this.state._project_id;
        let status = this.state._status

        const req = await changeProjectStatus({
            project_id, status
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

                <h1>Изменить статус проекта</h1>
                <div className="form" noValidate>
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
                        <label htmlFor="project_id" className="placeholder">ID проекта</label>
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
                            <option value={'CLOSED'}>CLOSED</option>
                        </select>
                        <div className="cut"></div>
                        <label htmlFor="status" className="placeholder">Статус</label>
                    </div>
                    <button
                        type="submit"
                        onClick={this.handleSubmit}
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="submit"
                    >
                        Изменить
                    </button>
                </div>
            </div>
        );
    }
}

export default ChangeProjectStatus;