/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetodepoo.view;

import javafx.scene.control.TableCell;
import projetodepoo.modelos.Time;

public class PosTabelaCell extends TableCell<Time, Integer> {
    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || getTableView() == null || getIndex() < 0 ||
            getIndex() >= getTableView().getItems().size()) {
            setText(null);
            setStyle("");
            return;
        }

        int posicao = getIndex() + 1;
        setText(String.valueOf(posicao));

        String bg; String fg;
        if (posicao <= 4) {            bg = "#1E90FF"; fg = "white"; }
        else if (posicao <= 6) {       bg = "#f58d42"; fg = "black"; }
        else if (posicao <= 12) {      bg = "#32CD32"; fg = "black"; }
        else if (posicao <= 16) {      bg = "#D3D3D3"; fg = "black"; }
        else {                         bg = "#FF4500"; fg = "white"; }

        setStyle(
            "-fx-background-color: -fx-table-cell-border-color, " + bg + ";" + "-fx-background-insets: 0, 1;" + "-fx-text-fill: " + fg + ";"
        );
    }
}
