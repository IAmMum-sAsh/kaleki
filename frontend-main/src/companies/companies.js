
import React, {Component} from 'react';
import './companies.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class Card extends React.Component {
    render() {
        return(
            <div className="card">
                <span className="comp-name"><span className="dot"></span>Name</span>

                <div className="close-form"></div>
                <div className="card-body">
                    <h2>ID: id</h2>
                    <br />
                    <h2>Директор компании:</h2> <h3>Name</h3>
                    <p>Вы можете связаться с ним
                        по всем, интересующим вас вопросам по электронной почте <a href="mailto:mail">mail</a></p>
                    <br/> <h5>(Address)</h5>
                </div>
            </div>
        )
    }
}

class Companies extends Component {
    constructor(props) {
        super(props);
        this.state = {
            companies: [],
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    async getCompanies() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        return await fetch('/api/companies', {
            method: 'get',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getCompanies();
        this.setState({companies: prs});
    }

    renderCompanies() {
        let companies = this.state.companies;

        if (companies == null) {return;}

        let projectCards = [];
        for (let i= 0; i < companies.length; ++i) {
            let clnm = "";
            if (i != 0){
                projectCards.push(
                    <a href={"/companies/"+companies[i].id}>
                        <div className="card">
                            <span className="comp-name"><span className={"company-dot "+clnm}></span>{companies[i].name}</span>

                            <input type="checkbox" className="close-form" onClick={event => {}}></input>
                            <div className="card-body">
                                <h2>ID: {companies[i].id}</h2>
                                <br />
                                <h2>Директор компании:</h2> <h3>{companies[i].ceo.username}</h3>
                                <p>Вы можете связаться с ним
                                    по всем, интересующим вас вопросам по электронной почте <a className="hvr" href={"mailto:"+companies[i].ceo.email}>{companies[i].ceo.email}</a></p>
                                <br/><h5>({companies[i].address})</h5>
                            </div>
                        </div>
                    </a>
                );
            }
        }

        return projectCards;
    }

    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <h1>Копмании</h1>
                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderCompanies()}
                    </div>

                </div>
            </div>
        );
    }
}

export default Companies;