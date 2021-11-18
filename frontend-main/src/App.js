import logo from './logo.svg';
import './App.css';
import {Component} from "react";
import {BrowserRouter as Router, Routes, Route, Switch, BrowserRouter} from "react-router-dom";
// import Layout from '../containers/Layout'
import 'bootstrap/dist/css/bootstrap.min.css';
import ErrorPage from "./ErrorPage";
import MainPage from "./main_page_dir/main_page";
import Login from "./login_component/Login";
import SignUP from "./login_component/SignUP";
import MyProjects from "./my_projects/my_projects";
import Companies from "./companies/companies";
import Projects from "./projects/projects";
import Workers from "./workers/workers";
import GiveManage from "./give_manage/give_manage";
import MyProjectById from "./my_projects_by_id/my_projects_by_id";
import CreateCompany from "./create_company/create_company";
import CreateProject from "./create_project/create_project";
import SetWorkerOnProject from "./set_worker_on_project/set_worker_on_project";
import ChangeProjectStatus from "./change_project_status/change_project_status";


class App extends Component {
  render()
  {
    return (
        // <div className="App">
        //   <header className="App-header">
        //     <img src={logo} className="App-logo" alt="logo" />
        //     <p>
        //       Edit <code>src/App.js</code> and save to reload.
        //     </p>
        //     <a
        //       className="App-link"
        //       href="https://reactjs.org"
        //       target="_blank"
        //       rel="noopener noreferrer"
        //     >
        //       Learn React
        //     </a>
        //   </header>
        // </div>


        // <Router>
        //     <Routes>
        //         <Route exact path="/" element={<MainPage/>} />
        //
        //         <Route exact path={'/login'} element={<Login/>}/>
        //         <Route exact path={'/signup'} element={<SignUP/>}/>
        //
        //         <Route exact path={'/repositories'} element={<Repositories/>}/>
        //         <Route path="/repository/:id" element={<RepositoryPage  />} />
        //
        //
        //         <Route path="*"  element={<ErrorPage code={404} description={'Страница не найдена.'}/>} />
        //     </Routes>
        // </Router>
        <BrowserRouter>
          <Switch>
                <Route exact path={'/'} component={MainPage}/>

                <Route exact path={'/login'} component={Login}/>
                <Route exact path={'/signup'} component={SignUP}/>

                <Route exact path={'/my_projects'} component={MyProjects}/>
                <Route exact path={'/my_projects/**'} component={MyProjectById}/>

                <Route exact path={'/companies'} component={Companies}/>
                <Route exact path={'/projects'} component={Projects}/>
                <Route exact path={'/workers'} component={Workers}/>

                <Route exact path={'/give_manage'} component={GiveManage}/>
                <Route exact path={'/create_company'} component={CreateCompany}/>
                <Route exact path={'/create_project'} component={CreateProject}/>
                <Route exact path={'/set_worker_on_project'} component={SetWorkerOnProject}/>
                <Route exact path={'/change_project_status'} component={ChangeProjectStatus}/>










            {/*<Route exact path={'/repositories'} component={Repositories}/>*/}
            {/*<Route path={"/repository/:id"} component={RepositoryPage} />*/}

            {/*<Route exact path='/'>*/}
            {/*  <Header/>*/}
            {/*  <Mainpage/>*/}
            {/*</Route>*/}

            {/*<Route exact path='/login'>*/}
            {/*  <Login/>*/}
            {/*</Route>*/}

            {/*
            <Route exact path={'/user/:userId'} component={UserProfile}/>
            <Route exact path='/admin/users' render={props => <AdminUsers{...props}/>}/>
            <Route exact path='/admin/lessons' render={props => <AdminLessons{...props}/>}/>
            <Route exact path='/verdicts'>
              <Header {...this.props}/>
              <VerdictsInfo/>
            </Route>
            <Route exact path='/course/:courseId' component={Course}/>
            <Route exact path='/admin/lessons/new' component={AdminCreateLesson}/>
            <Route exact path='/admin/signup' component={AdminSignup}/>
            <Route exact path='/about-courses'>
              <Header/>
              <AboutCourses/>
            </Route>
            <Route exact path='/course/:courseId/homework/' component={CourseHomework}/>
            <Route exact path='/course/:courseId/homework/:homeworkId'
                   render = {props => <Homework{...props} tab={'description'}/> }/>
            <Route exact path='/course/:courseId/homework/:homeworkId/problems/:problemNumber'
                   render = {props => <Homework{...props} tab={'problems'}/> }/>
            <Route exact path='/course/:courseId/homework/:homeworkId/submissions'
                   render = {props => <Homework{...props} tab={'submissions'}/> }/>
            <Route exact path='/about-statistics' component={AboutStatistics}/>
            */}

            <Route>
              <ErrorPage code={404} description={'Страница не найдена.'}/>
            </Route>

          </Switch>
        </BrowserRouter>
    );
  }
}

export default App;