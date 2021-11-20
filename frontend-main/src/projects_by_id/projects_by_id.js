
import React, {Component} from 'react';
import './projects_by_id.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class ProjectsById extends Component {
    constructor(props) {
        super(props);
        this.state = {
            page_id: 0,
            projects: [],
            role: '',
            code: props.code ? props.code : '666',
            description: props.description ? props.description : 'Unknown error'
        };

    }

    async getRole() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('');

        return await fetch('/api/get_my_role', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async getMyProjectById() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        let project_id =document.URL;

        let ind = project_id.lastIndexOf('projects/');

        project_id = project_id.slice(ind+9);
        const name = 'page_id';

        this.setState({
            [name]: project_id
        });

        return await fetch('/api/projects/'+Number(project_id), {
            method: 'get',
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getMyProjectById();
        this.setState({projects: prs});
        let prsRole = await this.getRole();
        this.setState({role: prsRole.role});
    }

    renderInfo(){
        let projects = this.state.projects;

        if (projects == null) {return;}

        let projectCards = [];

        let clnm = "";
        if (projects.status == "FROZEN") {clnm = "bluest";}
        else if (projects.status == "ACTIVE") {clnm = "greenst";}
        projectCards.push(
            <div className={"info "+clnm}>
                <h2>Проект: {projects.name} (ID: {projects.id})</h2>
                <h5>
                    Компания: {projects.company_name} (ID: {projects.company_id})<br/>
                    Гендиректор: {projects.ceo_username} (<a className="hvr-by-id" href={"mailto:"+projects.ceo_email}>{projects.ceo_email})</a><br/>
                    Дата начала: {projects.start_date}
                </h5>
            </div>
        );

        return projectCards;
    }

    renderUsers(){
        let projects = this.state.projects.users;

        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        if (projects == null || !a) {return;}

        let projectCards = [];

        for (let i=0; i<projects.length; ++i){
            projectCards.push(
                <div className="card">
                    <span className="comp-name"><span className={"worker-dot "}></span>{projects[i].username}</span>

                    <input type="checkbox" className="close-form" onClick={event => {}}></input>
                    <div className="card-body">
                        <h2>ID: {projects[i].id}</h2>
                        <br />
                        <p>Вы можете связаться с ним
                            по всем, интересующим вас вопросам по электронной почте <a href={"mailto:"+projects[i].email}>{projects[i].email}</a></p>
                    </div>
                </div>
            );
        }
        return projectCards;
    }


    render() {
        return (
            <div className="mainmainmain">
                <Header />

                {this.renderInfo()}

                <div className='page-wrap d-flex flex-row align-items-center pt-5 maindiv'>
                    <div className='my-cards'>
                        {this.renderUsers()}
                    </div>
                </div>
            </div>
        );
    }
}

export default ProjectsById;