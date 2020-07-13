package com.vb.lsb.california.tour;

import com.vb.lsb.california.tour.model.Difficulty;
import com.vb.lsb.california.tour.model.Region;
import com.vb.lsb.california.tour.service.TourPackageService;
import com.vb.lsb.california.tour.service.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class CaliforniaTourApplication implements CommandLineRunner {

    @Value("${data.importfile}")
    private String importFile;

    @Autowired
    private TourPackageService ITourPackageService;
    @Autowired
    private TourService TourService;

    public static void main(String[] args) {
		SpringApplication.run(CaliforniaTourApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        createTourAllPackages();
        createTours(importFile);
    }
/**
     * Initialize all the known tour packages
     */
	private void createTourAllPackages(){
        ITourPackageService.createTourPackage("BC", "Backpack Cal");
        ITourPackageService.createTourPackage("CC", "California Calm");
        ITourPackageService.createTourPackage("CH", "California Hot springs");
        ITourPackageService.createTourPackage("CY", "Cycle California");
        ITourPackageService.createTourPackage("DS", "From Desert to Sea");
        ITourPackageService.createTourPackage("KC", "Kids California");
        ITourPackageService.createTourPackage("NW", "Nature Watch");
        ITourPackageService.createTourPackage("SC", "Snowboard Cali");
        ITourPackageService.createTourPackage("TC", "Taste of California");
    }

    /**
     * Create tour entities from an external file
     */
    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
            TourService.createTour(importedTour.getTitle(),
                    importedTour.getDescription(),
                    importedTour.getBlurb(),
                    importedTour.getPrice(),
                    importedTour.getLength(),
                    importedTour.getBullets(),
                    importedTour.getKeywords(),
                    importedTour.getPackageType(),
                    importedTour.getDifficulty(),
                    importedTour.getRegion()));
    }

    /**
     * Helper class to import CaliforniaTour.json
     */
    private static class TourFromFile {
        //fields
        private String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;
        //reader
        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
        }
        protected TourFromFile(){}

        String getPackageType() { return packageType; }

        String getTitle() { return title; }

        String getDescription() { return description; }

        String getBlurb() { return blurb; }

        Integer getPrice() { return Integer.parseInt(price); }

        String getLength() { return length; }

        String getBullets() { return bullets; }

        String getKeywords() { return keywords; }

        Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

        Region getRegion() { return Region.findByLabel(region); }
    }
}
