/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.garudadb.main;

/**
 *
 * @author mardian
 */
import lombok.Getter;
import lombok.Setter;

public class Sequence {

    @Getter
    @Setter
    protected String _id;

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected String sequence;

    @Getter
    @Setter
    protected String author;


}

