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
    public List<String> brands;

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

    @JsonProperty("archeologyContextNumber")
    public String archeologyContextNumber;

    @JsonProperty("archeologySite")
    public String archeologySite;

    @JsonProperty("archeologyDescription")
    public String archeologyDescription;

    @JsonProperty("archeologyDistinguishingMarks")
    public String archeologyDistinguishingMarks;

    @JsonProperty("archeologyActivity")
    public String archeologyActivity;

    @JsonProperty("archeologySpecificActivity")
    public String archeologySpecificActivity;

    @JsonProperty("archeologyDecoration")
    public String archeologyDecoration;

    @JsonProperty("archeologyPattern")
    public String archeologyPattern;

    @JsonProperty("archeologyColour")
    public String archeologyColour;

    @JsonProperty("archeologyMoulding")
    public String archeologyMoulding;

    @JsonProperty("archeologyPlacement")
    public String archeologyPlacement;

    @JsonProperty("archeologyForm")
    public String archeologyForm;

    @JsonProperty("archeologyShape")
    public String archeologyShape;

    @JsonProperty("archeologyManufactureName")
    public String archeologyManufactureName;

    @JsonProperty("archeologyManufactureDate")
    public String archeologyManufactureDate;

    @JsonProperty("archeologyTechnique")
    public String archeologyTechnique;

    @JsonProperty("archeologyProvenance")
    public String archeologyProvenance;

    @JsonProperty("numismaticsDenomination")
    public String numismaticsDenomination;

    @JsonProperty("numismaticsDateIssued")
    public String numismaticsDateIssued;

    @JsonProperty("numismaticsSeries")
    public String numismaticsSeries;

    @JsonProperty("numismaticsMaterial")
    public String numismaticsMaterial;

    @JsonProperty("numismaticsAxis")
    public String numismaticsAxis;

    @JsonProperty("numismaticsEdgeDescription")
    public String numismaticsEdgeDescription;

    @JsonProperty("numismaticsObverseDescription")
    public String numismaticsObverseDescription;

    @JsonProperty("numismaticsReverseDescription")
    public String numismaticsReverseDescription;

    @JsonProperty("philatelyColour")
    public String philatelyColour;

    @JsonProperty("philatelyDenomination")
    public String philatelyDenomination;

    @JsonProperty("philatelyImprint")
    public String philatelyImprint;

    @JsonProperty("philatelyIssue")
    public String philatelyIssue;

    @JsonProperty("philatelyDateIssued")
    public String philatelyDateIssued;

    @JsonProperty("philatelyForm")
    public String philatelyForm;

    @JsonProperty("philatelyOverprint")
    public String philatelyOverprint;

    @JsonProperty("philatelyGibbonsNumber")
    public String philatelyGibbonsNumber;

    @JsonProperty("isdFormat")
    public String isdFormat;

    @JsonProperty("isdLanguage")
    public String isdLanguage;

    @JsonProperty("isdDescriptionOfContent")
    public String isdDescriptionOfContent;

    @JsonProperty("isdPeopleDepicted")
    public String isdPeopleDepicted;

    @JsonProperty("audioVisualRecordingDetails")
    public String audioVisualRecordingDetails;

    @JsonProperty("audioVisualContentSummaries")
    public List<String> audioVisualContentSummaries;

    @JsonProperty("tradeLiteratureNumberofPages")
    public String tradeLiteratureNumberofPages;

    @JsonProperty("tradeLiteraturePageSizeFormat")
    public String tradeLiteraturePageSizeFormat;

    @JsonProperty("tradeLiteratureCoverTitle")
    public String tradeLiteratureCoverTitle;

    @JsonProperty("tradeLiteraturePrimarySubject")
    public String tradeLiteraturePrimarySubject;

    @JsonProperty("tradeLiteraturePublicationDate")
    public String tradeLiteraturePublicationDate;

    @JsonProperty("tradeLiteratureIllustrationTypes")
    public String tradeLiteratureIllustrationTypes;

    @JsonProperty("tradeLiteraturePrintingTypes")
    public String tradeLiteraturePrintingTypes;

    @JsonProperty("tradeLiteraturePublicationTypes")
    public List<String> tradeLiteraturePublicationTypes;

    @JsonProperty("tradeLiteraturePrimaryRole")
    public String tradeLiteraturePrimaryRole;

    @JsonProperty("tradeLiteraturePrimaryName")
    public String tradeLiteraturePrimaryName;

    @JsonProperty("indigenousCulturesLocalities")
    public List<String> indigenousCulturesLocalities;

    @JsonProperty("indigenousCulturesCulturalGroups")
    public List<String> indigenousCulturesCulturalGroups;

    @JsonProperty("indigenousCulturesMedium")
    public String indigenousCulturesMedium;

    @JsonProperty("indigenousCulturesDescription")
    public String indigenousCulturesDescription;

    @JsonProperty("indigenousCulturesLocalName")
    public String indigenousCulturesLocalName;

    @JsonProperty("indigenousCulturesPhotographer")
    public String indigenousCulturesPhotographer;

    @JsonProperty("indigenousCulturesAuthor")
    public String indigenousCulturesAuthor;

    @JsonProperty("indigenousCulturesIllustrator")
    public String indigenousCulturesIllustrator;

    @JsonProperty("indigenousCulturesMaker")
    public String indigenousCulturesMaker;

    @JsonProperty("indigenousCulturesDate")
    public String indigenousCulturesDate;

    @JsonProperty("indigenousCulturesCollector")
    public String indigenousCulturesCollector;

    @JsonProperty("indigenousCulturesDateCollected")
    public String indigenousCulturesDateCollected;

    @JsonProperty("indigenousCulturesIndividualsIdentified")
    public String indigenousCulturesIndividualsIdentified;

    @JsonProperty("indigenousCulturesTitle")
    public String indigenousCulturesTitle;

    @JsonProperty("indigenousCulturesSheets")
    public String indigenousCulturesSheets;

    @JsonProperty("indigenousCulturesPages")
    public String indigenousCulturesPages;

    @JsonProperty("indigenousCulturesLetterTo")
    public String indigenousCulturesLetterTo;

    @JsonProperty("indigenousCulturesLetterFrom")
    public String indigenousCulturesLetterFrom;

    @JsonProperty("artworkMedium")
    public String artworkMedium;

    @JsonProperty("artworkTechnique")
    public String artworkTechnique;

    @JsonProperty("artworkSupport")
    public String artworkSupport;

    @JsonProperty("artworkPlateNumber")
    public String artworkPlateNumber;

    @JsonProperty("artworkDrawingNumber")
    public String artworkDrawingNumber;

    @JsonProperty("artworkState")
    public String artworkState;

    @JsonProperty("artworkPublisher")
    public String artworkPublisher;

    @JsonProperty("artworkPrimaryInscriptions")
    public String artworkPrimaryInscriptions;

    @JsonProperty("artworkSecondaryInscriptions")
    public String artworkSecondaryInscriptions;

    @JsonProperty("artworkTertiaryInscriptions")
    public String artworkTertiaryInscriptions;

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

    @JsonProperty("id")
    public String id;
}
