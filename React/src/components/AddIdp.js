import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import CommandService from "../services/command-service";

const required = value => {
  if (!value ) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

export default class AddIdp extends Component {
  constructor(props) {
    super(props);
    this.handleCreateIdp = this.handleCreateIdp.bind(this);
    this.onChangeIdpName = this.onChangeIdpName.bind(this);
    this.onChangeInstitutionName = this.onChangeInstitutionName.bind(this);

    this.state = {
      idpName: "",
      institutionName: "",
      loading: false,
      message: "",
      successMessage: ""
    };
  }

  onChangeIdpName(e) {
    this.setState({
      idpName: e.target.value
    });
  }

  onChangeInstitutionName(e) {
    this.setState({
      institutionName: e.target.value
    });
  }

  handleCreateIdp(e) {
    e.preventDefault();

    this.setState({
      message: "",
      loading: true,
      successMessage:""
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      CommandService.createIdp(this.state.idpName, this.state.institutionName).then(
        () => {
          //this.props.history.push("/addIdp");
          this.setState({
            loading: false,
            successMessage: 'Idp created successfully'
          });
         
          
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            loading: false,
            message: resMessage,
            successMessage:""
          });
        }
      );
    } else {
      this.setState({
        loading: false
      });
    }
  }

  render() {
    return (
     
      <div className="col-md-12">
        <h2>Add Identity Provider</h2>

        <div className="form form-container">
          <Form
            onSubmit={this.handleCreateIdp}
            ref={c => {
              this.form = c;
            }}
          >
            {this.state.successMessage && (
              <div className="form-group">
                <div className="alert" role="alert">
                 <b>{this.state.successMessage}</b>
                </div>
              </div>
            )}
            <div className="form-group">
              <label htmlFor="idpName">Name</label>
              <Input
                type="text"
                className="form-control"
                name="idpName"
                value={this.state.idpName}
                onChange={this.onChangeIdpName}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="institutionName">Institution</label>
              <Input
                type="text"
                className="form-control"
                name="institutionName"
                value={this.state.institutionName}
                onChange={this.onChangeInstitutionName}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <button
                className="btn btn-primary btn-block"
                disabled={this.state.loading}
              >
                {this.state.loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Add Idp</span>
              </button>
            </div>

            {this.state.message && (
              <div className="form-group">
                <div className="alert alert-danger" role="alert">
                  {this.state.message}
                </div>
              </div>
            )}
            
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}

