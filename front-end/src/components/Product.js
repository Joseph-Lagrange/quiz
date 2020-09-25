import React, {Component} from 'react';
import '../styles/Product.css';

class Product extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            price: '',
            unit: '',
            url: ''
        };
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8080/product', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state)
        }).then(response => response.json())
            .then(result => {
                alert("添加成功");
            })
            .catch(result => {
                console.log(result)
            })
    }

    render() {
        return (
            <div className="product">
                <h1>添加商品</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className="combination">
                        <div><label>名称:</label></div>
                        <div><input
                            type='text'
                            name='name'
                            onChange={this.handleChange}
                            value={this.state.name}/></div>
                    </div>

                    <div className="combination">
                        <div><label>价格:</label></div>
                        <div><input
                            type='text'
                            name='price'
                            onChange={this.handleChange}
                            value={this.state.price}/></div>
                    </div>

                    <div className="combination">
                        <div><label>单位:</label></div>
                        <div><input
                            type='text'
                            name='unit'
                            onChange={this.handleChange}
                            value={this.state.unit}/></div>
                    </div>

                    <div className="combination">
                        <div><label>图片:</label></div>
                        <div><input
                            type='text'
                            name='url'
                            onChange={this.handleChange}
                            value={this.state.url}/></div>
                    </div>

                    <div className="combination">
                        <div><input
                                className='submit'
                                type='submit'
                                value='提交'
                                style={{width:'60px',height:'25px',backgroundColor:'#1E96FC',color:'#FFFFFF',borderRadius:'8px',border:'none'}}
                                disabled={!this.state.name || !this.state.price || !this.state.unit || !this.state.url}/>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default Product;