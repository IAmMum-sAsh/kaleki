import React, {Component} from 'react';
import './about.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class About extends Component {
    constructor(props) {
        super(props);
        this.state = {
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        }
    }

    renderBtn() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');
        let b = cookies.get('username');

        if (a) {
            return <button onClick={e => {window.location='/projects'}}>Перейти к проектам</button>;
        }
    }

    render() {
        const {code, description} = this.state;
        return (
            <div className='main-important'>
                <Header />

                {/*<img className="baclimgmain" src="https://www.rabstol.net/uploads/gallery/main/496/rabstol_net_soup_17.jpg" />*/}
                <div className='mheader'>
                    <div className='mcontainer'>
                        <div className="overlay">
                            <h1>БОРЩ</h1>
                            <h3>Система управления персоналом</h3>
                            <p>
                                Курсовая
                            </p>
                            {/*<br/>*/}
                            {/*{this.renderBtn()}*/}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default About;