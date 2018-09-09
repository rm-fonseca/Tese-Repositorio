package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuseumVictoriaSpecimenModel {
    @JsonProperty("recordType")
    public String recordType;

    @JsonProperty("licence")
    public MuseumVictoriaLicenseModel licence;

    @JsonProperty("dateModified")
    public String dateModified;

    @JsonProperty("displayTitle")
    public String displayTitle;

    @JsonProperty("category")
    public String category;

    @JsonProperty("scientificGroup")
    public String scientificGroup;

    @JsonProperty("discipline")
    public String discipline;

    @JsonProperty("registrationNumber")
    public String registrationNumber;

    @JsonProperty("collectionNames")
    public List<String> collectionNames;

    @JsonProperty("type")
    public String type;

    @JsonProperty("classifications")
    public List<String> classifications;

    @JsonProperty("objectName")
    public String objectName;

    @JsonProperty("objectSummary")
    public String objectSummary;

    @JsonProperty("isdDescriptionOfContent")
    public String isdDescriptionOfContent;

    @JsonProperty("significance")
    public String significance;

    @JsonProperty("keywords")
    public String keywords;

    @JsonProperty("collectingAreas")
    public List<String> collectingAreas;

    @JsonProperty("associations")
    public List<MuseumVictoriaAssociationModel> associations;

    @JsonProperty("relatedItemIds")
    public List<String> relatedItemIds;

    @JsonProperty("relatedSpecimenIds")
    public List<String> relatedSpecimenIds;

    @JsonProperty("relatedArticleIds")
    public List<String> relatedArticleIds;

    @JsonProperty("relatedSpeciesIds")
    public List<String> relatedSpeciesIds;

    @JsonProperty("media")
    public List<MuseumVictoriaMediaModel> media;

    @JsonProperty("acquisitionInformation")
    public String acquisitionInformation;

    @JsonProperty("acknowledgement")
    public String acknowledgement;

    @JsonProperty("museumLocation")
    public MuseumVictoriaMuseumLocationModel museumLocation;

    @JsonProperty("numberOfSpecimens")
    public String numberOfSpecimens;

    @JsonProperty("clutchSize")
    public String clutchSize;

    @JsonProperty("sex")
    public String sex;

    @JsonProperty("stageOrAge")
    public String stageOrAge;

    @JsonProperty("storages")
    public List<MuseumVictoriaStoragesModel> storages;

    @JsonProperty("typeStatus")
    public String typeStatus;

    @JsonProperty("identifiedBy")
    public String identifiedBy;

    @JsonProperty("dateIdentified")
    public String dateIdentified;

    @JsonProperty("qualifier")
    public String qualifier;

    @JsonProperty("qualifierRank")
    public String qualifierRank;

    @JsonProperty("taxonomy")
    public MuseumVictoriaTaxonomyModel taxonomy;

    @JsonProperty("collectionEvent")
    public MuseumVictoriaCollectionEventModel collectionEvent;

    @JsonProperty("collectionSite")
    public MuseumVictoriaCollectionSiteModel collectionSite;

    @JsonProperty("palaeontologyDateCollectedFrom")
    public String palaeontologyDateCollectedFrom;

    @JsonProperty("palaeontologyDateCollectedTo")
    public String palaeontologyDateCollectedTo;

    @JsonProperty("mineralogySpecies")
    public String mineralogySpecies;

    @JsonProperty("mineralogyVariety")
    public String mineralogyVariety;

    @JsonProperty("mineralogyGroup")
    public String mineralogyGroup;

    @JsonProperty("mineralogyClass")
    public String mineralogyClass;

    @JsonProperty("mineralogyAssociatedMatrix")
    public String mineralogyAssociatedMatrix;

    @JsonProperty("mineralogyType")
    public String mineralogyType;

    @JsonProperty("mineralogyTypeOfType")
    public String mineralogyTypeOfType;

    @JsonProperty("meteoritesName")
    public String meteoritesName;

    @JsonProperty("meteoritesClass")
    public String meteoritesClass;

    @JsonProperty("meteoritesGroup")
    public String meteoritesGroup;

    @JsonProperty("meteoritesType")
    public String meteoritesType;

    @JsonProperty("meteoritesMinerals")
    public String meteoritesMinerals;

    @JsonProperty("meteoritesSpecimenWeight")
    public String meteoritesSpecimenWeight;

    @JsonProperty("meteoritesTotalWeight")
    public String meteoritesTotalWeight;

    @JsonProperty("meteoritesDateFell")
    public String meteoritesDateFell;

    @JsonProperty("meteoritesDateFound")
    public String meteoritesDateFound;

    @JsonProperty("tektitesName")
    public String tektitesName;

    @JsonProperty("tektitesClassification")
    public String tektitesClassification;

    @JsonProperty("tektitesShape")
    public String tektitesShape;

    @JsonProperty("tektitesLocalStrewnfield")
    public String tektitesLocalStrewnfield;

    @JsonProperty("tektitesGlobalStrewnfield")
    public String tektitesGlobalStrewnfield;

    @JsonProperty("petrologyRockClass")
    public String petrologyRockClass;

    @JsonProperty("petrologyRockGroup")
    public String petrologyRockGroup;

    @JsonProperty("petrologyRockName")
    public String petrologyRockName;

    @JsonProperty("petrologyRockDescription")
    public String petrologyRockDescription;

    @JsonProperty("petrologyMineralsPresent")
    public String petrologyMineralsPresent;

    @JsonProperty("id")
    public String id;

}
