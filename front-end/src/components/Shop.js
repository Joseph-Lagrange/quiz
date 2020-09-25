import React, {Component} from 'react';
import '../styles/Shop.css';

const URL = 'http://localhost:8080/products';

class Shop extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: []
        };
    }

    componentDidMount() {
        fetch(URL).then((response) => response.json())
            .then((result) => {
                // console.log(result);
                this.setState({
                    data: result
                });
            });
    }

    render() {
        let name = this.state.data[0].name;
        console.log(name);
        return (
            <div className="shop">

                <div style={{border:'solid #ededed',width:'150px',height:'250px',margin:'30px',float:'left'}}>

                </div>

            </div>
        );
    }
}

export default Shop;