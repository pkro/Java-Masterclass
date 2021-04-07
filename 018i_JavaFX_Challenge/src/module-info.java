module de.pkro.contactapp{
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;
    // https://stackoverflow.com/questions/53035454/javafx-module-javafx-graphics
    exports de.pkro.contactapp.datamodel;
    opens de.pkro.contactapp;
        }