/// <reference types="cypress" />
const HOST_URL = 'http://localhost:8082/';

describe('Add Idp User', () => {
 

  it('Add Idp User', () => {
    cy.visit(HOST_URL+'addIdpUser');
    cy.get("span").should('have.text', 'Add Idp User')
    cy.get('input[name=providerId]').type('IdP_009');
    cy.get('input[name=targetId]').type('mypssword');
    cy.get('input[name=federation]').type('SHIB');
    cy.get('input[name=idpId]').type('3');
    cy.get('input[name=eduPersonId]').type('edu123')
    .type('{enter}');
    cy.get("span").should('have.text', 'Add Idp User')
    
  })
  it('Add Idp Required', () => {
    cy.visit(HOST_URL+'addIdpUser');
    cy.get("span").should('have.text', 'Add Idp User')
    cy.get('input[name=providerId]').type('IdP_009');
    cy.get('input[name=targetId]').type('mypssword');
    cy.get('input[name=federation]').type('SHIB');
    cy.get('input[name=idpId]').type('3')
    .type('{enter}');
    cy.get(".alert").should('have.text', 'This field is required!')
  })

  it('Add Idp User', () => {
    cy.visit(HOST_URL+'addIdpUser');
    cy.get("span").should('have.text', 'Add Idp User')
    cy.get('input[name=providerId]').type('IdP_009');
    cy.get('input[name=targetId]').type('mypssword');
    cy.get('input[name=federation]').type('SHIB');
    cy.get('input[name=idpId]').type('3');
    cy.get('input[name=eduPersonId]').type('edu123')
    .type('{enter}');
    cy.get("span").should('have.text', 'Add Idp User')
    
  })
})
