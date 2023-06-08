import * as React from "react";

import { request, setAuthHeader } from "../helpers/axios_helper";

import Buttons from "./Buttons";
import AuthContent from "./AuthContent";
import LoginForm from "./LoginForm";
import WelcomeContent from "./WelcomeContent";
import TestReact from "./testReact";
import Magazines from "./magazines/Magazines";
import Magazine from "./magazines/Magazine";
import AllNews from "./news/AllNews";
import MyMagazines from "./magazines/MyMagazines";
import Recom from "./recommend/Recom";

export default class AppContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome",
            selectedMagazineId: null,
        };
    }

    login = () => {
        this.setState({ componentToShow: "login" });
    };

    logout = () => {
        this.setState({ componentToShow: "welcome" });
        setAuthHeader(null);
    };

    testReact = () => {
        this.setState({ componentToShow: "testReact" });
    };
    magazines = () => {
        this.setState({ componentToShow: "magazines" });
    };
    handleMagazineClick = (id) => {
        this.setState({ selectedMagazineId: id, componentToShow: "magazine" });
    };
    handleAllNewsClick = () => {
        this.setState({  componentToShow: "allNews" });
    };
    handleMyMagazinesClick = () => {
        this.setState({  componentToShow: "myMagazines" });
    };
    handleRecommendationsClick = () => {
        this.setState({  componentToShow: "recommendations" });
    };

    onLogin = (e, username, password) => {
        e.preventDefault();
        request("POST", "/login", {
            login: username,
            password: password,
        })
            .then((response) => {
                setAuthHeader(response.data.token);
                this.setState({ componentToShow: "messages" });
            })
            .catch((error) => {
                setAuthHeader(null);
                this.setState({ componentToShow: "welcome" });
            });
    };

    onRegister = (event, firstName, lastName, username, password) => {
        event.preventDefault();
        request("POST", "/register", {
            firstName: firstName,
            lastName: lastName,
            login: username,
            password: password,
        })
            .then((response) => {
                setAuthHeader(response.data.token);
                console.log(response.token);
                this.setState({ componentToShow: "messages" });
            })
            .catch((error) => {
                setAuthHeader(null);
                this.setState({ componentToShow: "welcome" });
            });
    };

    render() {
        return (
            <>  
                
                <Buttons
                    login={this.login}
                    logout={this.logout}
                    testReact={this.testReact}
                    magazines={this.magazines}
                    allNews={this.handleAllNewsClick}
                    myMagazines={this.handleMyMagazinesClick}
                    recommendations={this.handleRecommendationsClick}
                />

                {this.state.componentToShow === "welcome" && <WelcomeContent />}
                {this.state.componentToShow === "login" && (
                    <LoginForm
                        onLogin={this.onLogin}
                        onRegister={this.onRegister}
                    />
                )}
                {this.state.componentToShow === "messages" && <AuthContent />}
                {this.state.componentToShow === "testReact" && <TestReact />}
                {this.state.componentToShow === "magazines" && <Magazines handleMagazineClick={this.handleMagazineClick} />}
                {this.state.componentToShow === "magazine" && <Magazine id={this.state.selectedMagazineId}/>}
                {this.state.componentToShow === "allNews" && <AllNews />}
                {this.state.componentToShow === "myMagazines" && <MyMagazines handleMagazineClick={this.handleMagazineClick} />}
                {this.state.componentToShow === "recommendations" && <Recom />}
            </>
        );
    }
}
