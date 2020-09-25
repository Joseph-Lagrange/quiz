import React, {Component} from 'react';
import '../styles/Shop.css';

const URL = 'http://localhost:8080/products';

class Shop extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: {},
        };
    }

    componentDidMount() {
        fetch(URL).then((response) => response.json())
            .then((result) => {
                this.setState({
                    data: result
                });
            });
    }

    handler(key) {
        this.state.data[key].number = 1;
        fetch('http://localhost:8080/order', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.data[key])
        }).then(response => response.json())
            .then(result => {
                console.log(result)
            })
            .catch(result => {
                console.log(result)
            })
    }

    render() {
        return (
            <div className="shop">
                {Object.keys(this.state.data)
                    .map((key) => (
                        <div key={key} style={{
                            border: 'solid #ededed',
                            width: '150px',
                            height: '250px',
                            margin: '30px',
                            float: 'left'
                        }}>
                            <h3>{this.state.data[key].name}</h3>
                            <p>单价：{this.state.data[key].price}/{this.state.data[key].unit}</p>
                            <button
                                className="btn btn-success m-2"
                                style={{width:'30px',height:'30px',borderRadius:'50%',border:'none'}}
                                onClick={() => this.handler(key)}
                            >+
                            </button>
                        </div>
                    ))}
            </div>
        );
    }
}

export default Shop;