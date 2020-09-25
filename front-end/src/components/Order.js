import React, {Component} from 'react';
import '../styles/Order.css';

const URL = 'http://localhost:8080/orders';

class Order extends Component {

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

    render() {
        return (
            <div className="order">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <h3>名字</h3>
                        </td>
                        <td>
                            <h3>单价</h3>
                        </td>
                        <td>
                            <h3>数量</h3>
                        </td>
                        <td>
                            <h3>单位</h3>
                        </td>
                        <td>
                            <h3>操作</h3>
                        </td>
                    </tr>
                    {Object.keys(this.state.data)
                        .map((key) => (
                            <tr key={key}>
                                <td><p>{this.state.data[key].name}</p></td>
                                <td><p>{this.state.data[key].price}</p></td>
                                <td><p>{this.state.data[key].number}</p></td>
                                <td><p>{this.state.data[key].unit}</p></td>
                                <td>
                                    <button>+</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Order;