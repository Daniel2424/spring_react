import * as React from 'react';

import { request, setAuthHeader } from '../../helpers/axios_helper';

export default class AllNews extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
			visibleCount: 10

        }
    };

    componentDidMount() {
        request(
            "GET",
            "/allNews",
            {}).then(
            (response) => {
                this.setState({data: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
                }

            }
        );
    };
	showMoreNews = () => {
        const { visibleCount } = this.state;
        this.setState({ visibleCount: visibleCount + 10 });
    };

  render() {
	const { data, visibleCount } = this.state;
    const visibleNews = data.slice(0, visibleCount);
    return (
        <div className="row justify-content-md-center">
            <div className="col-4">
                <div className="card" style={{width: "36rem"}}>
                    <div className="card-body">
                        <h5 className="card-title">Все новости</h5>
                        <ul>
                            {visibleNews.map((line) =>
									<div>
										<div>topic: {line.topic}</div>
										<div>{line.newsText}</div>
										<a href={line.url} key={line}>ссылка на статью </a>
										<hr/>
									</div>  
                                )
                            }
                        </ul>
                    </div>
					{visibleCount < data.length && (
                            <button onClick={this.showMoreNews}>Показать еще</button>
                        )}
                    
                </div>
                <div>
                       
                </div>
            </div>
        </div>
    );
  };
}