import * as React from "react";

import { request, setAuthHeader } from "../../helpers/axios_helper";

export default class Magazines extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
        };
    }

    componentDidMount() {
        request("GET", "/allMagazines", {})
            .then((response) => {
                this.setState({ data: response.data });
            })
            .catch((error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({ data: error.response.code });
                }
            });
    }

    render() {
        return (
            <div className="row justify-content-md-center">
                <div className="col-4">
                    <div className="card" style={{ width: "40rem" }}>
                        <div className="card-body">
                            <h5 className="card-title">
                                Все журналы:
                            </h5>
                            <ul>
                                {this.state.data &&
                                    this.state.data.map((line) => (
                                        <div>
                                            <li   key={line}>{line.topic} </li>
                                            <div> {line.title}</div>
                                            
                                            <img width={200} height={250} src={process.env.PUBLIC_URL + line.img} alt="Картинка" style={{ border: "2px solid black" }} />
                                            <div> {line.description}</div>
                                            <div key={line.id} onClick={() => this.props.handleMagazineClick(line.id)} style={{ textDecoration: 'underline', cursor: 'pointer' }}> Смотреть...</div>
                                            
                                            
                                            
                                            <hr/>
                                        </div>
                                    ))}
                            </ul>
                        </div>
                    </div>
                    <div></div>
                </div>
            </div>
        );
    }
}
