
import React, {Component} from 'react';
import './workers.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class Card extends React.Component {
    render() {
        return(
            <div className="card">
                <span className="comp-name"><span className="dot"></span>username</span>

                <div className="close-form"></div>
                <div className="card-body">
                    <h2>ID: id</h2>
                    <br />
                    <p>Вы можете связаться с ним
                        по всем, интересующим вас вопросам по электронной почте <a href="mailto:email">email</a></p>
                    <br/>
                </div>
            </div>
        )
    }
}

class Workers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            workers: [],
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    async getWorkers() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        return await fetch('/api/workers', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getWorkers();
        this.setState({workers: prs});
    }

    renderWorkers() {
        let workers = this.state.workers;

        if (workers == null) {return;}

        let projectCards = [];
        for (let i= 0; i < workers.length; ++i) {
            let clnm = "";
            projectCards.push(
                <div className="card">
                    <span className="comp-name"><span className={"worker-dot "+clnm}></span>{workers[i].username}</span>

                    <input type="checkbox" className="close-form" onClick={event => {}}></input>
                    <div className="card-body">
                        <h2>ID: {workers[i].id}</h2>
                        <br />
                        <p>Вы можете связаться с ним
                            по всем, интересующим вас вопросам по электронной почте <a className="hvr" href={"mailto:"+workers[i].email}>{workers[i].email}</a></p>
                    </div>
                </div>
            );
        }

        return projectCards;
    }

    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <h1>Ваши коллеги</h1>
                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderWorkers()}
                    </div>

                </div>
            </div>
        );
    }
}

export default Workers;