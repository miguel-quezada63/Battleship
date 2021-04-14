module Battleship {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    opens sample;
    opens sample.controller;
    opens sample.model;
}