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
    public List<AMuseumVictoriaMediaModel> media;

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
    public Object stageOrAge;

    @JsonProperty("storages")
    public List<MuseumVictoriaStoragesModel> storages;

    @JsonProperty("typeStatus")
    public Object typeStatus;

    @JsonProperty("identifiedBy")
    public Object identifiedBy;

    @JsonProperty("dateIdentified")
    public Object dateIdentified;

    @JsonProperty("qualifier")
    public Object qualifier;

    @JsonProperty("qualifierRank")
    public Object qualifierRank;

    @JsonProperty("taxonomy")
    public MuseumVictoriaTaxonomyModel taxonomy;

    @JsonProperty("collectionEvent")
    public MuseumVictoriaCollectionEventModel collectionEvent;

    @JsonProperty("collectionSite")
    public MuseumVictoriaCollectionSiteModel collectionSite;

    @JsonProperty("palaeontologyDateCollectedFrom")
    public Object palaeontologyDateCollectedFrom;

    @JsonProperty("palaeontologyDateCollectedTo")
    public Object palaeontologyDateCollectedTo;

    @JsonProperty("mineralogySpecies")
    public Object mineralogySpecies;

    @JsonProperty("mineralogyVariety")
    public Object mineralogyVariety;

    @JsonProperty("mineralogyGroup")
    public Object mineralogyGroup;

    @JsonProperty("mineralogyClass")
    public Object mineralogyClass;

    @JsonProperty("mineralogyAssociatedMatrix")
    public Object mineralogyAssociatedMatrix;

    @JsonProperty("mineralogyType")
    public Object mineralogyType;

    @JsonProperty("mineralogyTypeOfType")
    public Object mineralogyTypeOfType;

    @JsonProperty("meteoritesName")
    public Object meteoritesName;

    @JsonProperty("meteoritesClass")
    public Object meteoritesClass;

    @JsonProperty("meteoritesGroup")
    public Object meteoritesGroup;

    @JsonProperty("meteoritesType")
    public Object meteoritesType;

    @JsonProperty("meteoritesMinerals")
    public Object meteoritesMinerals;

    @JsonProperty("meteoritesSpecimenWeight")
    public Object meteoritesSpecimenWeight;

    @JsonProperty("meteoritesTotalWeight")
    public Object meteoritesTotalWeight;

    @JsonProperty("meteoritesDateFell")
    public Object meteoritesDateFell;

    @JsonProperty("meteoritesDateFound")
    public Object meteoritesDateFound;

    @JsonProperty("tektitesName")
    public Object tektitesName;

    @JsonProperty("tektitesClassification")
    public Object tektitesClassification;

    @JsonProperty("tektitesShape")
    public Object tektitesShape;

    @JsonProperty("tektitesLocalStrewnfield")
    public Object tektitesLocalStrewnfield;

    @JsonProperty("tektitesGlobalStrewnfield")
    public Object tektitesGlobalStrewnfield;

    @JsonProperty("petrologyRockClass")
    public Object petrologyRockClass;

    @JsonProperty("petrologyRockGroup")
    public Object petrologyRockGroup;

    @JsonProperty("petrologyRockName")
    public Object petrologyRockName;

    @JsonProperty("petrologyRockDescription")
    public Object petrologyRockDescription;

    @JsonProperty("petrologyMineralsPresent")
    public Object petrologyMineralsPresent;

    @JsonProperty("id")
    public String id;

}
