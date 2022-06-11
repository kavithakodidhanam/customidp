/// <reference types="cypress" />
const HOST_URL = 'http://localhost:8082/';
const username = 'Admin';
const password = 'admin';


describe('Login', () => {
  it('successfully loads', () => {
    cy.visit(HOST_URL)
  })

  it('displays login page', () => {
    cy.visit(HOST_URL)
    cy.get("span").should('have.text', 'Login')
  })

  it('perform login', () => {
    cy.visit(HOST_URL)
    cy.get('input[name=username]').type(username);
    cy.get('input[name=password]').type(password).type('{enter}');

    cy.get("b").should('have.text', 'Search By ProviderId Idp Users in Custom Idp ')
    cy.get("a").should('have.text', 'Custom Identity ProviderDashboardIdpUserLogOut')
    cy.url().should('include','/home')
  })
  it('perform login failure', () => {
    cy.visit(HOST_URL)
    cy.get('input[name=username]').type(username);
    cy.get('input[name=password]').type(password+'1').type('{enter}');
    cy.get(".alert").should('have.text', 'Bad credentials')
  })

  it('perform required failure', () => {
    cy.visit(HOST_URL)
    cy.get('input[name=username]').type(username).type('{enter}');
    cy.get(".alert").should('have.text', 'This field is required!')
  })
})
