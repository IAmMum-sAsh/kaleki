import React, { Component } from 'react';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import './help_component.css';
import 'bootstrap/dist/css/bootstrap.min.css';

class HelpPositionComponent extends Component {
    static propTypes = {
        cookies: instanceOf(Cookies).isRequired
    };

    constructor(props) {
        super(props);
        const { cookies } = props;
        this.state = {
            positions: [],
            code: props.code ? props.code : '999',
            description: props.description ? props.description : 'Unknown error'
        };

    }

    async getPositions() {
        const cookies = new Cookies();
        let a = cookies.get('accessToken');

        return await fetch('/api/get_positions', {
            method: 'get',
            headers: new Headers({
                'Authorization': 'Bearer ' + a,
                'Content-Type': 'application/json'
            }),
        }).then(response => response.json());
    }

    async componentDidMount() {
        let prs = await this.getPositions();
        this.setState({positions: prs});
    }

    renderAllPositions(){
        let positions = this.state.positions;

        if (positions == null) {return;}

        function byField(field) {
            return (a, b) => a[field] > b[field] ? 1 : -1;
        }

        positions.sort(byField('id'));

        let positionsCards = [];
        for (let i= 0; i < positions.length; ++i) {
            if (positions[1].id != 1){
                positionsCards.push(
                    <p>{positions[i].id} - {positions[i].name}</p>
                );
            }

        }
        return positionsCards;
    }



    render() {
        return (
            <div className="help">
                <p>Возможные должности:</p>
                {this.renderAllPositions()}
            </div>
        );
    }
}

export default HelpPositionComponent;