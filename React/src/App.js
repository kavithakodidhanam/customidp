import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import AuthService from "./services/auth.service";
import Login from "./components/login";
import Home from "./components/home";
import AddIdp from "./components/AddIdp";
import AddIdpUser from "./components/AddIdpUser";
import EventBus from "./common/EventBus";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showAdminBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
    }
    
    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser,  showAdminBoard } = this.state;

    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/home"} className="navbar-brand">
            Custom Identity Provider
          </Link>
          
          <div className="navbar-nav mr-auto">
            {currentUser && (
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Dashboard
              </Link>
            </li>
            )}
            {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/addIdp"} className="nav-link">
                  Idp
                </Link>
              </li>
            )}

          {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/addIdpUser"} className="nav-link">
                  User
                </Link>
              </li>
            )}

           
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/login"]} component={Login} />
            <Route exact path="/home" component={Home} />
            <Route path="/addIdp" component={AddIdp} />
            <Route path="/addIdpUser" component={AddIdpUser} />
          </Switch>
        </div>

      </div>
    );
  }
}

export default App;
