package com.ekojean.patikaclone.Views.Patika;

import com.ekojean.patikaclone.views.MainLayout;
import com.ekojean.patikaclone.Controller.PatikaController;
import com.ekojean.patikaclone.Entities.Patika;
import com.ekojean.patikaclone.Interfaces.IController;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Patika")
@Route(value = "patika", layout = MainLayout.class)
@PermitAll
public class PatikaView extends VerticalLayout {

    Grid<Patika> patikaGrid = new Grid<>();

    List<Patika> patikaList = new ArrayList<>();

    IController<Patika> patikaController;

    Button patikaGuncelleButton = new Button(new Icon(VaadinIcon.REFRESH));
    Button patikaKolonAyarButton = new Button(new Icon(VaadinIcon.EYE));

    public PatikaView(PatikaController patikaController) {
        this.patikaController = patikaController;

        patikaGridLoad();
        patikaGridConfig();

        add(
                getHeader(),
                getBody()
        );
    }

    // Header
    private HorizontalLayout getHeader() {
        HorizontalLayout headerLayout = new HorizontalLayout();

        headerLayout.add(
                patikaGuncelleButton,
                patikaKolonAyarButton
        );

        return headerLayout;
    }

    // Body
    private VerticalLayout getBody() {
        VerticalLayout bodyLayout = new VerticalLayout();

        bodyLayout.add(
            patikaGrid
        );

        return bodyLayout;
    }

    // Gride servisten patikaları çekme
    private void patikaGridLoad() {
        patikaList = patikaController.getList(null);
        patikaGrid.setItems(patikaList);
    }

    // Patika grid ayarları
    private void patikaGridConfig() {
        Grid.Column<Patika> patikaRefNo = patikaGrid.addColumn(Patika::getPatikarefno).setHeader("Patika Ref No").setFooter("Toplam Kayıt: " + patikaList.size()).setSortable(true);
        Grid.Column<Patika> patikaAdi = patikaGrid.addColumn(Patika::getAdi).setHeader("Patika Adı").setSortable(true);
        Grid.Column<Patika> patikaAciklama = patikaGrid.addColumn(Patika::getAciklama).setHeader("Patika Açıklama").setSortable(true);
        Grid.Column<Patika> patikaPuan = patikaGrid.addColumn(Patika::getPuan).setHeader("Puan").setSortable(true);
        Grid.Column<Patika> patikaSaat = patikaGrid.addColumn(Patika::getSaat).setHeader("Saat").setSortable(true);
        Grid.Column<Patika> patikaAktif = patikaGrid.addComponentColumn(Patika -> createStatusIcon(Patika.getAktif())).setHeader("Aktif").setSortable(true);

        patikaKolonAyarButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        ColumnToggleContextMenu columnToggleContextMenu = new ColumnToggleContextMenu(patikaKolonAyarButton);
        columnToggleContextMenu.addColumnToggleItem("Patika Ref No", patikaRefNo);
        columnToggleContextMenu.addColumnToggleItem("Patika Adı", patikaAdi);
        columnToggleContextMenu.addColumnToggleItem("Patika Açıklama", patikaAciklama);
        columnToggleContextMenu.addColumnToggleItem("Patika Puan", patikaPuan);
        columnToggleContextMenu.addColumnToggleItem("Patika Saat", patikaSaat);
        columnToggleContextMenu.addColumnToggleItem("Aktif", patikaAktif);
    }

    // Patika Kolon İcon Ayar
    private Icon createStatusIcon(String status) {
        boolean isAvailable = "T".equals(status);
        Icon icon;
        if (isAvailable) {
            icon = VaadinIcon.CHECK.create();
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = VaadinIcon.CLOSE_SMALL.create();
            icon.getElement().getThemeList().add("badge error");
        }
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

    // Patika KolonAyar Menü Ayarı
    private static class ColumnToggleContextMenu extends ContextMenu {
        public ColumnToggleContextMenu(Component target) {
            super(target);
            setOpenOnClick(true);
        }


        void addColumnToggleItem(String label, Grid.Column<Patika> column) {
            MenuItem menuItem = this.addItem(label, e -> {
                column.setVisible(e.getSource().isChecked());
            });
            menuItem.setCheckable(true);
            menuItem.setChecked(column.isVisible());
            menuItem.setKeepOpen(true);
        }
    }
}
