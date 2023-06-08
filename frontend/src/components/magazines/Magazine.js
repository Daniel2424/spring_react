import * as React from "react";

import { request, setAuthHeader } from "../../helpers/axios_helper";

export default class Magazine extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
			isAddedToFavorites: false,
        };
		
    }

    componentDidMount() {
		const  id  = this.props.id;
        request("GET", "/magazine/" + id, {})
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
	addMagazine = (id)  =>{
        request("GET", "/addMagazine/"+ id, {})
            .then((response) => {
                console.log(response.data);
				// Показать сообщение о добавлении в избранное
				this.setState({ isAddedToFavorites: true });

				// Скрыть сообщение через 1 секунду
				setTimeout(() => {
				  this.setState({ isAddedToFavorites: false });
				}, 1000);
            })
            .catch((error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({ data: error.response.code });
                }
            });
    };
	

    render() {
        return (
            <div style={{marginBottom: "100px"}} className="row justify-content-md-center">
                <div className="col-4">
                    <div className="card" style={{ width: "50rem" }}>
                        <div className="card-body">
                            <h5 className="card-title">
                                Страница Журнала:
                            </h5>
                            
                                        <div>
                                            
										
											<li> {this.state.data.title}</li>
											<img width={400} height={500} src={process.env.PUBLIC_URL + this.state.data.img} alt="Картинка" style={{ border: "2px solid black" }} />
                                            <div> {this.state.data.description}</div>                                       
                                            <div key={this.state.data.id} onClick={() => this.addMagazine(this.state.data.id)} style={{ textDecoration: 'underline', cursor: 'pointer' }}>Добавить в избранное</div>{this.state.isAddedToFavorites && <div>Товар добавлен в избранное</div>}
											
                                            
                                        </div>
                                    
                            
                        </div>
                    </div>
                    <div></div>
                </div>
            </div>
        );
    }
}
