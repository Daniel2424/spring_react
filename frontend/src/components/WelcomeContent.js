import * as React from 'react';

export default class WelcomeContent extends React.Component {

  render() {
    return (
        <div style={{marginLeft:'30%', marginTop:'15%' }} className="row justify-content-md-center">
            <div className="jumbotron jumbotron-fluid">
              <div className="container">
                <h1 className="display-4">Новостной сайт </h1>
                <p className="lead">Авторизируйтесь в системе</p>
              </div>
            </div>
        </div>
    );
  };
}