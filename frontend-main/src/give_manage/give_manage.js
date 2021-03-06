
import React, {Component} from 'react';
import './give_manage.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";
import PropTypes from "prop-types";

async function giveManagePut(credentials) { //credentials as param
    console.log(JSON.stringify(credentials));
    const cookies = new Cookies();
    let a = cookies.get('accessToken');
    let data = '';
    return fetch('/api/give_manage', {
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

class GiveManage extends Component {
    static contextTypes = {
        router: PropTypes.object
    }
    constructor(props, context) {
        super(props, context);
        this.state = {
            _id: 1,
            warn: '',
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        };

        this.handleSubmit = this.handleSubmit.bind(this);

        //const [token, setToken] = useState();
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

        let id = this.state._id;

        const token = await giveManagePut({
            id
        });
        const cookies = new Cookies();
        cookies.set('id', token.id, {path: '/give_manage'});

        if (token.id < 0){
            this.setState({
                ['warn']: token.email
            });
            STYLE.worn = {display: 'block'};
        } else{
            this.props.history.push("/give_manage");
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

                <h1>???????? ?????????????????? ?????????? ??????????????????</h1>
                <div className="form" noValidate>
                    <div className="input-container ic1">
                        <input
                            className="input" type="number" min="2" placeholder=" "
                            id="id"
                            label="ID"
                            name="_id"
                            onChange={this.handleInputChange}
                            autoFocus
                        />
                        <div className="cut"></div>
                        <label htmlFor="ID" className="placeholder">ID</label>
                    </div>
                    <button
                        type="submit"
                        onClick={this.handleSubmit}
                        fullWidth
                        variant="contained"
                        color="primary"
                        className="submit"
                    >
                        ????????????????
                    </button>
                </div>
            </div>
        );
    }
}

export default GiveManage;