import React, {Component} from 'react';
import './companies_by_id.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class CompaniesById extends Component {
    constructor(props) {
        super(props);
        this.state = {
            page_id: 0,
            projects: [],
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        };

    }

    async getMyProjectById() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        let project_id =document.URL;

        let ind = project_id.lastIndexOf('companies/');

        project_id = project_id.slice(ind+10);
        const name = 'page_id';

        this.setState({
            [name]: project_id
        });

        return await fetch('/api/companies/'+Number(project_id), {
            method: 'get',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getMyProjectById();
        this.setState({projects: prs});
    }

    renderInfo(){
        let projects = this.state.projects;

        if (projects == null) {return;}

        let projectCards = [];

        projectCards.push(
            <div className={"company-info"}>
                <h2>Компания: {projects.name} (ID: {projects.id})</h2>
                <h5>
                    Гендиректор: {projects.ceo_username} (<a className="hvr-by-id" href={"mailto:"+projects.ceo_email}>{projects.ceo_email})</a><br/>
                    Адрес: {projects.address}
                </h5>
            </div>
        );

        return projectCards;
    }

    renderProjects(){
        let projects = this.state.projects.projects;

        if (projects == null) {return;}

        let projectCards = [];

        for (let i= 0; i < projects.length; ++i) {
            let clnm = "";
            if (projects[i].status == "FROZEN") {clnm = "bluest";}
            else if (projects[i].status == "ACTIVE") {clnm = "greenst";}
            projectCards.push(
                <a href={"/projects/"+projects[i].id}>
                    <div className="card">
                        <span className="comp-name"><span className={"dot "+clnm}></span>{projects[i].name}</span>

                        <input type="checkbox" className="close-form" onClick={event => {}}></input>
                        <div className="card-body">
                            <h2>ID: {projects[i].id}</h2>
                            <h2>Дата начала: {projects[i].start_date}</h2>
                            <br />
                            <h2>Менеджер проекта:</h2> <h3>{projects[i].company.ceo.username}</h3>
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

                {this.renderInfo()}

                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderProjects()}
                    </div>
                </div>
            </div>
        );
    }
}

export default CompaniesById;