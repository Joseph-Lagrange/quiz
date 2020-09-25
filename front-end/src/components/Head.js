import React, {Component} from 'react';
import {BrowserRouter, Switch, Route, Link, NavLink} from 'react-router-dom';
import '../styles/Head.css';

class Head extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="head">
                <div><NavLink to='/shop' style={{textDecoration: 'none'}}>商城</NavLink></div>
                <div><NavLink to='/order' style={{textDecoration: 'none'}}>订单</NavLink></div>
                <div><NavLink to='/product' style={{textDecoration: 'none'}}>添加商品</NavLink></div>
            </div>
        );
    }
}

export default Head;