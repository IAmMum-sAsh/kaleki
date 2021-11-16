import React, {Component} from "react";
// import Header from "../header/Header";
import './main_page.css';
import Header from "../header/Header";
import Cookies from "universal-cookie";

class MainPage extends Component {
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
                            <p>Не следует, однако забывать, что консультация с широким активом требуют от нас анализа новых предложений. Товарищи! рамки и место обучения кадров в значительной степени обуславливает создание системы обучения кадров, соответствует насущным потребностям. Равным образом укрепление и развитие структуры требуют определения и уточнения модели развития. Таким образом новая модель организационной деятельности позволяет выполнять важные задания по разработке позиций, занимаемых участниками в отношении поставленных задач. С другой стороны дальнейшее развитие различных форм деятельности позволяет выполнять важные задания по разработке направлений прогрессивного развития.</p>
                            {/*<br/>*/}
                            {/*{this.renderBtn()}*/}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default MainPage;