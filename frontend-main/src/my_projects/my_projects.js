
import React, {Component} from 'react';
import './my_projects.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class Card extends React.Component {
    render() {
        return(
            <div className="card">
                <span className="comp-name"><span className="dot"></span>Название</span>

                <div className="close-form"></div>
                <div className="card-body">
                    <h2>Дата начала: 2020-09-28</h2>
                    <br />
                    <h2>Менеджер проекта:</h2> <h3>Соловьёв Дмитрий Ксандрович</h3>
                    <p>Вы можете связаться с ним
                    по всем, интересующим вас вопросам по электронной почте <a href="mailto:sdx@big.data">sdx@big.data</a></p>
                    <br/>
                    <h5>Компания: Просрочка</h5> <h5>(Калининградская область, город Подольск, бульвар Балканская, 92)</h5>
                </div>
            </div>
        )
    }
}

class MyProjects extends Component {
    constructor(props) {
        super(props);
        this.state = {
            projects: [],
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    async getMyProjectsInfo() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        return await fetch('/api/my_projects', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getMyProjectsInfo();
        this.setState({projects: prs});
    }

    renderProjects() {
        let projects = this.state.projects;

        if (projects == null) {return;}

        let projectCards = [];
        for (let i= 0; i < projects.length; ++i) {
            let clnm = "";
            if (projects[i].status == "FROZEN") {clnm = "bluest";}
            else if (projects[i].status == "ACTIVE") {clnm = "greenst";}
            projectCards.push(
                <a href={"/my_projects/"+projects[i].id}>
                <div className="card">
                    <span className="comp-name"><span className={"dot "+clnm}></span>{projects[i].name}</span>

                    <input type="checkbox" className="close-form" onClick={event => {}}></input>
                    <div className="card-body">
                        <h2>ID: {projects[i].id}</h2>
                        <h2>Дата начала: {projects[i].start_date}</h2>
                        <br />
                        <h2>Менеджер проекта:</h2> <h3 className="black-h3">{projects[i].company.ceo.username}</h3>
                        <p>Вы можете связаться с ним
                            по всем, интересующим вас вопросам по электронной почте <a className="hvr" href={"mailto:"+projects[i].company.ceo.email}>{projects[i].company.ceo.email}</a></p>
                        <br/>
                        <h5>Компания: {projects[i].company.name}</h5> <h5>({projects[i].company.address})</h5>
                    </div>
                </div>
                </a>
            );
        }

        return projectCards;
    }

    render() {
        const {code, description} = this.state;
        return (
            <div className="mainmainmain">
                <Header />

                <h1>Ваши проекты</h1>
                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderProjects()}
                    </div>

                </div>
            </div>
        );
    }
}

export default MyProjects;