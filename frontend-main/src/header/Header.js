import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import './Header.css';
import 'bootstrap/dist/css/bootstrap.min.css';

class Header extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const { cookies } = props;
        this.state = {
            user: cookies.get("username") || "",
            data_p: 'avatar.png',
            userhref: '/login',
            role: '',
            name: '',
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        };

    }

    handleRemoveCookie = () => {
        const { cookies } = this.props;
        cookies.remove("username"); // remove the cookie
        cookies.remove("accessToken"); // remove the cookie
        cookies.remove("refreshToken"); // remove the cookie
        // this.setState({ user: cookies.get("user") });
    };

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

    async getName() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        return await fetch('/api/get_name', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prsRole = await this.getRole();
        this.setState({role: prsRole.role});
        let prsName = await this.getName();
        this.setState({name: prsName.name});
    }

    renderGiveManage() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = this.state.role;

        if (a && (b == "ROLE_MANAGER")) {
            return <a className='p-2 text-white' href='/give_manage'>Повысить</a>;
        }
    }

    renderCreateCompany() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = this.state.role;

        if (a && (b == "ROLE_MANAGER")) {
            return <a className='p-2 text-white' href='/create_company'>Создать компанию</a>;
        }
    }

    renderCreateProject() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = this.state.role;

        if (a && (b == "ROLE_MANAGER")) {
            return <a className='p-2 text-white' href='/create_project'>Создать проект</a>;
        }
    }

    renderSetWorkerOnProject() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = this.state.role;

        if (a && (b == "ROLE_MANAGER")) {
            return <a className='p-2 text-white' href='/set_worker_on_project'>Поставить работника на проект</a>;
        }
    }

    renderChangeProjectStatus() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = this.state.role;

        if (a && (b == "ROLE_MANAGER")) {
            return <a className='p-2 text-white' href='/change_project_status'>Изменить статус проекта</a>;
        }
    }

    addFunctions() {
        const cookies = new Cookies();
        let b = cookies.get('username');

        if (b) { //войдено
            return (
                <div className="dropdown-child">
                    <a href="/my_projects">Мои проекты</a>
                    {/*<a href="/user_settings">Настройки</a>*/}
                    {this.renderGiveManage()}
                    {this.renderCreateCompany()}
                    {this.renderCreateProject()}
                    {this.renderSetWorkerOnProject()}
                    {this.renderChangeProjectStatus()}
                    <a href="/" onClick={this.handleRemoveCookie} >Выйти</a>
                </div>
            )
        } else {
            return (
                <div className="dropdown-child">
                    <a href="/login">Войти</a>
                    <a href="/signup">Зарегистрироваться</a>
                </div>
            )
        }
    }
    renderBtnWork() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        if (a) {
            return <a className='p-2 text-white' href='/workers'>Работники</a>;
        }
    }

    renderName() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        if (a) {
            return <span className="user-span">для пользователя <span className="user-name">{this.state.name}</span></span>;
        }
    }

    render() {
        const { data_p } = this.state;
        const { userhref } = this.state;

        console.log(this.state.name + ' ' + this.state.role)
        return (
            <div>
                {/*<AuthElement />*/}
                {/*<CheckAcsessComponent />*/}

                <div className='d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm opana '>
                    <h5 className='my-0 mr-md-auto font-weight-bold'><a className='main-lbl p-2 text-dark' href='/'>БОРЩ</a>{this.renderName()}</h5>


                    <nav className='my-2 my-md-0 mr-md-3'>
                        {/*<a className='p-2 text-dark' href='/user/1'>Личный кабинет</a>*/}
                        <a className='p-2 text-white' href='/companies'>Компании</a>
                        <a className='p-2 text-white' href='/projects'>Проекты</a>
                        {this.renderBtnWork()}

                        {/*<a href={userhref} >*/}
                        {/*    <img className='user-nav-img' src={data_p} />*/}

                        {/*</a>*/}

                        <div className="dropdown">
                            <div >
                                <img className='user-nav-img' src={data_p}  alt='avatar'/>

                            </div>
                            { this.addFunctions() }
                        </div>
                    </nav>
                    {/*<a className='btn btn-outline-primary' href='/#'>Выход</a>*/}

                </div>
            </div>
        );
    }
}

export default withCookies(Header);