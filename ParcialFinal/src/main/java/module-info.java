module org.alexsv.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens org.alexsv.parcialfinal to javafx.fxml;
    exports org.alexsv.parcialfinal;
    exports org.alexsv.parcialfinal.Classes;
    opens org.alexsv.parcialfinal.Classes to javafx.fxml;
    //exports org.alexsv.parcialfinal.controllers;
    //exports org.alexsv.parcialfinal.controllers.reports;
    //opens org.alexsv.parcialfinal.controllers.reports to javafx.fxml;
}