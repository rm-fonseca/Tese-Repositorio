package repository.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuseumVictoriaItemModel {
    @JsonProperty("recordType")
    public String recordType;

    @JsonProperty("comments")
    public List<MuseumVictoriaCommentModel> comments;

    @JsonProperty("licence")
    public MuseumVictoriaLicenseModel licence;

    @JsonProperty("dateModified")
    public String dateModified;

    @JsonProperty("displayTitle")
    public String displayTitle;

    @JsonProperty("category")
    public String category;

    @JsonProperty("discipline")
    public String discipline;

    @JsonProperty("type")
    public String type;

    @JsonProperty("registrationNumber")
    public String registrationNumber;

    @JsonProperty("collectionNames")
    public List<String> collectionNames;

    @JsonProperty("collectingAreas")
    public List<String> collectingAreas;

    @JsonProperty("classifications")
    public List<String> classifications;

    @JsonProperty("objectName")
    public String objectName;

    @JsonProperty("objectSummary")
    public String objectSummary;

    @JsonProperty("physicalDescription")
    public String physicalDescription;

    @JsonProperty("inscription")
    public String inscription;

    @JsonProperty("associations")
    public List<MuseumVictoriaAssociationModel> associations;

    @JsonProperty("keywords")
    public List<String> keywords;

    @JsonProperty("significance")
    public String significance;

    @JsonProperty("modelScale")
    public String modelScale;

    @JsonProperty("shape")
    public String shape;

    @JsonProperty("dimensions")
    public List<MuseumVictoriaDimensionModel> dimensions;

    @JsonProperty("references")
    public String references;

    @JsonProperty("bibliographies")
    public List<String> bibliographies;

    @JsonProperty("modelNames")
    public List<String> modelNames;

    @JsonProperty("brands")
    public List<Object> brands;

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
    public Object acknowledgement;

    @JsonProperty("museumLocation")
    public MuseumVictoriaMuseumLocationModel museumLocation;

    @JsonProperty("archeologyContextNumber")
    public Object archeologyContextNumber;

    @JsonProperty("archeologySite")
    public Object archeologySite;

    @JsonProperty("archeologyDescription")
    public Object archeologyDescription;

    @JsonProperty("archeologyDistinguishingMarks")
    public Object archeologyDistinguishingMarks;

    @JsonProperty("archeologyActivity")
    public Object archeologyActivity;

    @JsonProperty("archeologySpecificActivity")
    public Object archeologySpecificActivity;

    @JsonProperty("archeologyDecoration")
    public Object archeologyDecoration;

    @JsonProperty("archeologyPattern")
    public Object archeologyPattern;

    @JsonProperty("archeologyColour")
    public Object archeologyColour;

    @JsonProperty("archeologyMoulding")
    public Object archeologyMoulding;

    @JsonProperty("archeologyPlacement")
    public Object archeologyPlacement;

    @JsonProperty("archeologyForm")
    public Object archeologyForm;

    @JsonProperty("archeologyShape")
    public Object archeologyShape;

    @JsonProperty("archeologyManufactureName")
    public Object archeologyManufactureName;

    @JsonProperty("archeologyManufactureDate")
    public Object archeologyManufactureDate;

    @JsonProperty("archeologyTechnique")
    public Object archeologyTechnique;

    @JsonProperty("archeologyProvenance")
    public Object archeologyProvenance;

    @JsonProperty("numismaticsDenomination")
    public Object numismaticsDenomination;

    @JsonProperty("numismaticsDateIssued")
    public Object numismaticsDateIssued;

    @JsonProperty("numismaticsSeries")
    public Object numismaticsSeries;

    @JsonProperty("numismaticsMaterial")
    public Object numismaticsMaterial;

    @JsonProperty("numismaticsAxis")
    public Object numismaticsAxis;

    @JsonProperty("numismaticsEdgeDescription")
    public Object numismaticsEdgeDescription;

    @JsonProperty("numismaticsObverseDescription")
    public Object numismaticsObverseDescription;

    @JsonProperty("numismaticsReverseDescription")
    public Object numismaticsReverseDescription;

    @JsonProperty("philatelyColour")
    public Object philatelyColour;

    @JsonProperty("philatelyDenomination")
    public Object philatelyDenomination;

    @JsonProperty("philatelyImprint")
    public Object philatelyImprint;

    @JsonProperty("philatelyIssue")
    public Object philatelyIssue;

    @JsonProperty("philatelyDateIssued")
    public Object philatelyDateIssued;

    @JsonProperty("philatelyForm")
    public Object philatelyForm;

    @JsonProperty("philatelyOverprint")
    public Object philatelyOverprint;

    @JsonProperty("philatelyGibbonsNumber")
    public Object philatelyGibbonsNumber;

    @JsonProperty("isdFormat")
    public Object isdFormat;

    @JsonProperty("isdLanguage")
    public Object isdLanguage;

    @JsonProperty("isdDescriptionOfContent")
    public Object isdDescriptionOfContent;

    @JsonProperty("isdPeopleDepicted")
    public Object isdPeopleDepicted;

    @JsonProperty("audioVisualRecordingDetails")
    public Object audioVisualRecordingDetails;

    @JsonProperty("audioVisualContentSummaries")
    public List<Object> audioVisualContentSummaries;

    @JsonProperty("tradeLiteratureNumberofPages")
    public Object tradeLiteratureNumberofPages;

    @JsonProperty("tradeLiteraturePageSizeFormat")
    public Object tradeLiteraturePageSizeFormat;

    @JsonProperty("tradeLiteratureCoverTitle")
    public Object tradeLiteratureCoverTitle;

    @JsonProperty("tradeLiteraturePrimarySubject")
    public Object tradeLiteraturePrimarySubject;

    @JsonProperty("tradeLiteraturePublicationDate")
    public Object tradeLiteraturePublicationDate;

    @JsonProperty("tradeLiteratureIllustrationTypes")
    public Object tradeLiteratureIllustrationTypes;

    @JsonProperty("tradeLiteraturePrintingTypes")
    public Object tradeLiteraturePrintingTypes;

    @JsonProperty("tradeLiteraturePublicationTypes")
    public List<Object> tradeLiteraturePublicationTypes;

    @JsonProperty("tradeLiteraturePrimaryRole")
    public Object tradeLiteraturePrimaryRole;

    @JsonProperty("tradeLiteraturePrimaryName")
    public Object tradeLiteraturePrimaryName;

    @JsonProperty("indigenousCulturesLocalities")
    public List<Object> indigenousCulturesLocalities;

    @JsonProperty("indigenousCulturesCulturalGroups")
    public List<Object> indigenousCulturesCulturalGroups;

    @JsonProperty("indigenousCulturesMedium")
    public Object indigenousCulturesMedium;

    @JsonProperty("indigenousCulturesDescription")
    public Object indigenousCulturesDescription;

    @JsonProperty("indigenousCulturesLocalName")
    public Object indigenousCulturesLocalName;

    @JsonProperty("indigenousCulturesPhotographer")
    public Object indigenousCulturesPhotographer;

    @JsonProperty("indigenousCulturesAuthor")
    public Object indigenousCulturesAuthor;

    @JsonProperty("indigenousCulturesIllustrator")
    public Object indigenousCulturesIllustrator;

    @JsonProperty("indigenousCulturesMaker")
    public Object indigenousCulturesMaker;

    @JsonProperty("indigenousCulturesDate")
    public Object indigenousCulturesDate;

    @JsonProperty("indigenousCulturesCollector")
    public Object indigenousCulturesCollector;

    @JsonProperty("indigenousCulturesDateCollected")
    public Object indigenousCulturesDateCollected;

    @JsonProperty("indigenousCulturesIndividualsIdentified")
    public Object indigenousCulturesIndividualsIdentified;

    @JsonProperty("indigenousCulturesTitle")
    public Object indigenousCulturesTitle;

    @JsonProperty("indigenousCulturesSheets")
    public Object indigenousCulturesSheets;

    @JsonProperty("indigenousCulturesPages")
    public Object indigenousCulturesPages;

    @JsonProperty("indigenousCulturesLetterTo")
    public Object indigenousCulturesLetterTo;

    @JsonProperty("indigenousCulturesLetterFrom")
    public Object indigenousCulturesLetterFrom;

    @JsonProperty("artworkMedium")
    public Object artworkMedium;

    @JsonProperty("artworkTechnique")
    public Object artworkTechnique;

    @JsonProperty("artworkSupport")
    public Object artworkSupport;

    @JsonProperty("artworkPlateNumber")
    public Object artworkPlateNumber;

    @JsonProperty("artworkDrawingNumber")
    public Object artworkDrawingNumber;

    @JsonProperty("artworkState")
    public Object artworkState;

    @JsonProperty("artworkPublisher")
    public Object artworkPublisher;

    @JsonProperty("artworkPrimaryInscriptions")
    public Object artworkPrimaryInscriptions;

    @JsonProperty("artworkSecondaryInscriptions")
    public Object artworkSecondaryInscriptions;

    @JsonProperty("artworkTertiaryInscriptions")
    public Object artworkTertiaryInscriptions;

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

    @JsonProperty("id")
    public String id;
}
