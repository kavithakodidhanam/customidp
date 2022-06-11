/// <reference types="cypress" />
const HOST_URL = 'http://localhost:8082/';


describe('Add IDP', () => {
  
  it('Add Idp', () => {
    cy.visit(HOST_URL+'addIdp');
    cy.get("span").should('have.text', 'Add Idp')
    cy.get('input[name=idpName]').type('IdP_009');
    cy.get('input[name=institutionName]').type('institutionName').type('{enter}');
    cy.get("span").should('have.text', 'Add Idp')
    
  })
  it('Add Idp Required', () => {
    cy.visit(HOST_URL+'addIdp');
    cy.get("span").should('have.text', 'Add Idp')
    cy.get('input[name=idpName]').type('IdP_009').type('{enter}');
    cy.get(".alert").should('have.text', 'This field is required!')
  })
})
