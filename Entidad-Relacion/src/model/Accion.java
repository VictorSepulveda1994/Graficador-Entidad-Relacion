/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author EquipoRocket
 */
public class Accion {
    TipoDeAccion tipo;
    Element elemento;
    Element elemento2;
    Diagram diagram;

    public Accion(TipoDeAccion tipo, Element elemento) {
        this.tipo = tipo;
        this.elemento = elemento;
        this.diagram=new Diagram();
    }

    public TipoDeAccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeAccion tipo) {
        this.tipo = tipo;
    }

    public Element getElemento() {
        return elemento;
    }

    public void setElemento(Element elemento) {
        this.elemento = elemento;
    }

    public Element getElemento2() {
        return elemento2;
    }

    public void setElemento2(Element elemento2) {
        this.elemento2 = elemento2;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
}
