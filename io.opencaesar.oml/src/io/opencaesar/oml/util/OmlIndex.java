/**
 * 
 * Copyright 2019 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package io.opencaesar.oml.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;

import io.opencaesar.oml.Annotation;
import io.opencaesar.oml.AnnotationProperty;
import io.opencaesar.oml.AnnotationPropertyReference;
import io.opencaesar.oml.Aspect;
import io.opencaesar.oml.AspectReference;
import io.opencaesar.oml.Classifier;
import io.opencaesar.oml.Concept;
import io.opencaesar.oml.ConceptInstance;
import io.opencaesar.oml.ConceptInstanceReference;
import io.opencaesar.oml.ConceptReference;
import io.opencaesar.oml.ConceptTypeAssertion;
import io.opencaesar.oml.Element;
import io.opencaesar.oml.Entity;
import io.opencaesar.oml.EntityPredicate;
import io.opencaesar.oml.EnumeratedScalar;
import io.opencaesar.oml.EnumeratedScalarReference;
import io.opencaesar.oml.FacetedScalar;
import io.opencaesar.oml.FacetedScalarReference;
import io.opencaesar.oml.FeatureProperty;
import io.opencaesar.oml.KeyAxiom;
import io.opencaesar.oml.LinkAssertion;
import io.opencaesar.oml.NamedInstance;
import io.opencaesar.oml.OmlPackage;
import io.opencaesar.oml.QuotedLiteral;
import io.opencaesar.oml.Relation;
import io.opencaesar.oml.RelationCardinalityRestrictionAxiom;
import io.opencaesar.oml.RelationEntity;
import io.opencaesar.oml.RelationEntityPredicate;
import io.opencaesar.oml.RelationEntityReference;
import io.opencaesar.oml.RelationInstance;
import io.opencaesar.oml.RelationInstanceReference;
import io.opencaesar.oml.RelationPredicate;
import io.opencaesar.oml.RelationRangeRestrictionAxiom;
import io.opencaesar.oml.RelationReference;
import io.opencaesar.oml.RelationRestrictionAxiom;
import io.opencaesar.oml.RelationTargetRestrictionAxiom;
import io.opencaesar.oml.RelationTypeAssertion;
import io.opencaesar.oml.Rule;
import io.opencaesar.oml.RuleReference;
import io.opencaesar.oml.Scalar;
import io.opencaesar.oml.ScalarProperty;
import io.opencaesar.oml.ScalarPropertyCardinalityRestrictionAxiom;
import io.opencaesar.oml.ScalarPropertyRangeRestrictionAxiom;
import io.opencaesar.oml.ScalarPropertyReference;
import io.opencaesar.oml.ScalarPropertyRestrictionAxiom;
import io.opencaesar.oml.ScalarPropertyValueAssertion;
import io.opencaesar.oml.SpecializableTerm;
import io.opencaesar.oml.SpecializationAxiom;
import io.opencaesar.oml.Structure;
import io.opencaesar.oml.StructureInstance;
import io.opencaesar.oml.StructureReference;
import io.opencaesar.oml.StructuredProperty;
import io.opencaesar.oml.StructuredPropertyCardinalityRestrictionAxiom;
import io.opencaesar.oml.StructuredPropertyRangeRestrictionAxiom;
import io.opencaesar.oml.StructuredPropertyReference;
import io.opencaesar.oml.StructuredPropertyRestrictionAxiom;
import io.opencaesar.oml.StructuredPropertyValueAssertion;

/*
 * This API assumes the resource set has a CrossReferenceAdapter installed on all EObjects
 */
public class OmlIndex {

	// Core Index API
	
	private static List<EObject> findInverseReferencers(Element element, EReference eReference) {
        final Set<EObject> referencers = new HashSet<>();
        final ECrossReferenceAdapter adapter = ECrossReferenceAdapter.getCrossReferenceAdapter(element);
		Collection<Setting> settings;
		if (adapter != null) {
			// the fast method
			settings = adapter.getInverseReferences(element, true);
		} else {
			// the slow method
			settings = searchForReferences(element);
		};
        for (Setting setting : settings) {
            if (setting.getEStructuralFeature() == eReference) {
                referencers.add(setting.getEObject());
            }
        }
        return new ArrayList<EObject>(referencers);
    }

	private static Collection<Setting> searchForReferences(EObject element) {
		final Resource resource = element.eResource();
	    if (resource == null) {
			final EObject rootEObject = EcoreUtil.getRootContainer(element);
	    	return UsageCrossReferencer.find(element, rootEObject);
	    } else {
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet == null) {
	        	return UsageCrossReferencer.find(element, resource);
	      	} else {
	        	return UsageCrossReferencer.find(element, resourceSet);
	      	}
	    }
	}

	private static <T extends EObject> List<T> findInverseReferencers(Element element, Class<T> type, EReference eReference) {
        final Set<T> referencers = new HashSet<>(); 
		findInverseReferencers(element, eReference).forEach(referencer -> {
			if (type.isInstance(referencer)) {
				referencers.add(type.cast(referencer));
			}
		});
		return new ArrayList<T>(referencers);
    }


	// Element

	// Annotation

	// AnnotatedElement

	// IdentifiedElement
	
	// Ontology

	// VocabularyBox

	// Vocabulary

	// VocabularyBundle
	
	// DescriptionBox

	// Description
	
	// DescriptionBundle

	// Member
	
	// Term

	// SpecializableTerm

	public static List<SpecializationAxiom> findSpecializationAxiomsWithSpecializedTerm(SpecializableTerm term) {
		return findInverseReferencers(term, SpecializationAxiom.class, OmlPackage.Literals.SPECIALIZATION_AXIOM__SPECIALIZED_TERM);
	}

	// Type

	// Classifier
	
	public static List<FeatureProperty> findFeaturePropertiesWithDomain(Classifier classifier) {
		return findInverseReferencers(classifier, FeatureProperty.class, OmlPackage.Literals.FEATURE_PROPERTY__DOMAIN);
	}

	// Entity

	public static List<RelationEntity> findRelationEntitiesWithSource(Entity entity) {
		return findInverseReferencers(entity, RelationEntity.class, OmlPackage.Literals.RELATION_ENTITY__SOURCE);
	}

	public static List<RelationEntity> findRelationEntitiesWithTarget(Entity entity) {
		return findInverseReferencers(entity, RelationEntity.class, OmlPackage.Literals.RELATION_ENTITY__TARGET);
	}

	public static List<RelationRangeRestrictionAxiom> findRelationRangeRestrictionAxiomsWithRange(Entity entity) {
		return findInverseReferencers(entity, RelationRangeRestrictionAxiom.class, OmlPackage.Literals.RELATION_RANGE_RESTRICTION_AXIOM__RANGE);
	}

	public static List<RelationCardinalityRestrictionAxiom> findRelationCardinalityRestrictionAxiomsWithRange(Entity entity) {
		return findInverseReferencers(entity, RelationCardinalityRestrictionAxiom.class, OmlPackage.Literals.RELATION_CARDINALITY_RESTRICTION_AXIOM__RANGE);
	}

	public static List<EntityPredicate> findEntityPredicatesWithEntity(Entity entity) {
		return findInverseReferencers(entity, EntityPredicate.class, OmlPackage.Literals.ENTITY_PREDICATE__ENTITY);
	}

	// Aspect

	public static List<AspectReference> findAspectReferencesWithAspect(Aspect aspect) {
		return findInverseReferencers(aspect, AspectReference.class, OmlPackage.Literals.ASPECT_REFERENCE__ASPECT);
	}

	// Concept

	public static List<ConceptReference> findConceptReferencesWithConcept(Concept concept) {
		return findInverseReferencers(concept, ConceptReference.class, OmlPackage.Literals.CONCEPT_REFERENCE__CONCEPT);
	}

	public static List<ConceptTypeAssertion> findConceptTypeAssertionsWithType(Concept concept) {
		return findInverseReferencers(concept, ConceptTypeAssertion.class, OmlPackage.Literals.CONCEPT_TYPE_ASSERTION__TYPE);
	}

	// RelationEntity
	
	public static List<RelationEntityReference> findRelationEntityReferencesWithEntity(RelationEntity entity) {
		return findInverseReferencers(entity, RelationEntityReference.class, OmlPackage.Literals.RELATION_ENTITY_REFERENCE__ENTITY);
	}

	public static List<RelationTypeAssertion> findRelationTypeAssertionsWithType(RelationEntity entity) {
		return findInverseReferencers(entity, RelationTypeAssertion.class, OmlPackage.Literals.RELATION_TYPE_ASSERTION__TYPE);
	}

	public static List<RelationEntityPredicate> findRelationEntityPredicatesWithEntity(RelationEntity entity) {
		return findInverseReferencers(entity, RelationEntityPredicate.class, OmlPackage.Literals.RELATION_ENTITY_PREDICATE__ENTITY);
	}

	// Structure

	public static List<StructuredProperty> findStructuredPropertiesWithRange(Structure structure) {
		return findInverseReferencers(structure, StructuredProperty.class, OmlPackage.Literals.STRUCTURED_PROPERTY__RANGE);
	}

	public static List<StructureInstance> findStructureInstancesWithType(Structure structure) {
		return findInverseReferencers(structure, StructureInstance.class, OmlPackage.Literals.STRUCTURE_INSTANCE__TYPE);
	}

	public static List<StructureReference> findStructureReferencesWithStructure(Structure structure) {
		return findInverseReferencers(structure, StructureReference.class, OmlPackage.Literals.STRUCTURE_REFERENCE__STRUCTURE);
	}

	public static List<StructuredPropertyRangeRestrictionAxiom> findStructuredPropertyRangeRestrictionAxiomsWithRange(Structure structure) {
		return findInverseReferencers(structure, StructuredPropertyRangeRestrictionAxiom.class, OmlPackage.Literals.STRUCTURED_PROPERTY_RANGE_RESTRICTION_AXIOM__RANGE);
	}

	public static List<StructuredPropertyCardinalityRestrictionAxiom> findStructuredPropertyCardinalityRestrictionAxiomsWithRange(Structure structure) {
		return findInverseReferencers(structure, StructuredPropertyCardinalityRestrictionAxiom.class, OmlPackage.Literals.STRUCTURED_PROPERTY_CARDINALITY_RESTRICTION_AXIOM__RANGE);
	}

	// Property

	// AnnotationProperty

	public static List<AnnotationPropertyReference> findAnnotationPropertyReferencesWithProperty(AnnotationProperty property) {
		return findInverseReferencers(property, AnnotationPropertyReference.class, OmlPackage.Literals.ANNOTATION_PROPERTY_REFERENCE__PROPERTY);
	}

	public static List<Annotation> findAnnotationsWithProperty(AnnotationProperty property) {
		return findInverseReferencers(property, Annotation.class, OmlPackage.Literals.ANNOTATION__PROPERTY);
	}

	// FeatureProperty

	// ScalarProperty

	public static List<ScalarPropertyReference> findScalarPropertyReferencesWithProperty(ScalarProperty property) {
		return findInverseReferencers(property, ScalarPropertyReference.class, OmlPackage.Literals.SCALAR_PROPERTY_REFERENCE__PROPERTY);
	}

	public static List<ScalarPropertyRestrictionAxiom> findScalarPropertyRestrictionAxiomsWithProperty(ScalarProperty property) {
		return findInverseReferencers(property, ScalarPropertyRestrictionAxiom.class, OmlPackage.Literals.SCALAR_PROPERTY_RESTRICTION_AXIOM__PROPERTY);
	}

	public static List<ScalarPropertyValueAssertion> findScalarPropertyValueAssertionsWithProperty(ScalarProperty property) {
		return findInverseReferencers(property, ScalarPropertyValueAssertion.class, OmlPackage.Literals.SCALAR_PROPERTY_VALUE_ASSERTION__PROPERTY);
	}

	public static List<KeyAxiom> findKeyAxiomWithProperty(ScalarProperty property) {
		return findInverseReferencers(property, KeyAxiom.class, OmlPackage.Literals.KEY_AXIOM__PROPERTIES);
	}

	// StructuredProperty

	public static List<StructuredPropertyReference> findStructuredPropertyReferencesWithProperty(StructuredProperty property) {
		return findInverseReferencers(property, StructuredPropertyReference.class, OmlPackage.Literals.STRUCTURED_PROPERTY_REFERENCE__PROPERTY);
	}

	public static List<StructuredPropertyRestrictionAxiom> findStructuredPropertyRestrictionAxiomsWithProperty(StructuredProperty property) {
		return findInverseReferencers(property, StructuredPropertyRestrictionAxiom.class, OmlPackage.Literals.STRUCTURED_PROPERTY_RESTRICTION_AXIOM__PROPERTY);
	}

	public static List<StructuredPropertyValueAssertion> findStructuredPropertyValueAssertionsWithProperty(StructuredProperty property) {
		return findInverseReferencers(property, StructuredPropertyValueAssertion.class, OmlPackage.Literals.STRUCTURED_PROPERTY_VALUE_ASSERTION__PROPERTY);
	}

	// Scalar

	public static List<ScalarProperty> findScalarPropertiesWithRange(Scalar scalar) {
		return findInverseReferencers(scalar, ScalarProperty.class, OmlPackage.Literals.SCALAR_PROPERTY__RANGE);
	}

	public static List<ScalarPropertyRangeRestrictionAxiom> findScalarPropertyRangeRestrictionAxiomsWithRange(Scalar scalar) {
		return findInverseReferencers(scalar, ScalarPropertyRangeRestrictionAxiom.class, OmlPackage.Literals.SCALAR_PROPERTY_RANGE_RESTRICTION_AXIOM__RANGE);
	}

	public static List<ScalarPropertyCardinalityRestrictionAxiom> findScalarPropertyCardinalityRestrictionAxiomsWithRange(Scalar scalar) {
		return findInverseReferencers(scalar, ScalarPropertyCardinalityRestrictionAxiom.class, OmlPackage.Literals.SCALAR_PROPERTY_CARDINALITY_RESTRICTION_AXIOM__RANGE);
	}

	public static List<QuotedLiteral> findQuotedLiteralsWithType(Scalar scalar) {
		return findInverseReferencers(scalar, QuotedLiteral.class, OmlPackage.Literals.QUOTED_LITERAL__TYPE);
	}

	// FacetedScalar

	public static List<FacetedScalarReference> findFacetedScalarReferencesWithScalar(FacetedScalar scalar) {
		return findInverseReferencers(scalar, FacetedScalarReference.class, OmlPackage.Literals.FACETED_SCALAR_REFERENCE__SCALAR);
	}

	// EnumeratedScalar

	public static List<EnumeratedScalarReference> findEnumeratedScalarReferencesWithScalar(EnumeratedScalar scalar) {
		return findInverseReferencers(scalar, EnumeratedScalarReference.class, OmlPackage.Literals.ENUMERATED_SCALAR_REFERENCE__SCALAR);
	}

	// Relation

	public static List<RelationReference> findRelationReferencesWithRelation(Relation relation) {
		return findInverseReferencers(relation, RelationReference.class, OmlPackage.Literals.RELATION_REFERENCE__RELATION);
	}

	public static List<RelationRestrictionAxiom> findRelationRestrictionAxiomsWithRelation(Relation relation) {
		return findInverseReferencers(relation, RelationRestrictionAxiom.class, OmlPackage.Literals.RELATION_RESTRICTION_AXIOM__RELATION);
	}

	public static List<LinkAssertion> findLinkAssertionsWithRelation(Relation relation) {
		return findInverseReferencers(relation, LinkAssertion.class, OmlPackage.Literals.LINK_ASSERTION__RELATION);
	}

	public static List<RelationPredicate> findRelationPredicatesWithRelation(Relation relation) {
		return findInverseReferencers(relation, RelationPredicate.class, OmlPackage.Literals.RELATION_PREDICATE__RELATION);
	}

	// ForwardRelation

	// ReverseRelation

	// Rule

	public static List<RuleReference> findRuleReferencesWithRule(Rule rule) {
		return findInverseReferencers(rule, RuleReference.class, OmlPackage.Literals.RULE_REFERENCE__RULE);
	}

	// Instance

	// StructureInstance

	// NamedInstance

	public static List<RelationInstance> findRelationInstancesWithSource(NamedInstance instance) {
		return findInverseReferencers(instance, RelationInstance.class, OmlPackage.Literals.RELATION_INSTANCE__SOURCES);
	}
	
	public static List<RelationInstance> findRelationInstancesWithTarget(NamedInstance instance) {
		return findInverseReferencers(instance, RelationInstance.class, OmlPackage.Literals.RELATION_INSTANCE__TARGETS);
	}

	public static List<LinkAssertion> findLinkAssertionsWithTarget(NamedInstance instance) {
		return findInverseReferencers(instance, LinkAssertion.class, OmlPackage.Literals.LINK_ASSERTION__TARGET);
	}

	public static List<RelationTargetRestrictionAxiom> findRelationTargetRestrictionAxiomsWithTarget(NamedInstance instance) {
		return findInverseReferencers(instance, RelationTargetRestrictionAxiom.class, OmlPackage.Literals.RELATION_TARGET_RESTRICTION_AXIOM__TARGET);
	}

	// ConceptInstance

	public static List<ConceptInstanceReference> findConceptInstanceReferencesWithInstance(ConceptInstance instance) {
		return findInverseReferencers(instance, ConceptInstanceReference.class, OmlPackage.Literals.CONCEPT_INSTANCE_REFERENCE__INSTANCE);
	}

	// RelationInstance

	public static List<RelationInstanceReference> findRelationInstanceReferencesWithInstance(RelationInstance instance) {
		return findInverseReferencers(instance, RelationInstanceReference.class, OmlPackage.Literals.RELATION_INSTANCE_REFERENCE__INSTANCE); 
	}

	// Statement

	// VocabularyStatement
	
	// DescriptionStatement

	// Import
		
	// VocabularyImport

	// VocabularyExtension
	
	// VocabularyUsage

	// VocabularyBundleImport

	// VocabularyBundleInclusion
	
	// VocabularyBundleExtension
	
	// DescriptionImport

	// DescriptionUsage

	// DescriptionExtension	

	// DescriptionBundleImport

	// DescriptionBundleInclusion
	
	// DescriptionBundleExtension

	// DescriptionBundleUsage

	// Axiom

	// SpecializationAxiom

	// RestrictionAxiom

	// PropertyRestrictionAxiom

	// ScalarPropertyRestrictionAxiom

	// ScalarPropertyRangeRestrictionAxiom

	// ScalarPropertyCardinalityRestrictionAxiom

	// ScalarPropertyValueRestrictionAxiom

	// StructuredPropertyRestrictionAxiom

	// StructuredPropertyRangeRestrictionAxiom

	// StructuredPropertyCardinalityRestrictionAxiom

	// StructuredPropertyValueRestrictionAxiom
	
	// RelationRestrictionAxiom

	// RelationRangeRestrictionAxiom
	
	// RelationCardinalityRestrictionAxiom

	// RelationTargetRestrictionAxiom

	// Assertion
	
	// TypeAssertion

	// ConceptTypeAssertion

	// RelationypeAssertion

	// PropertyValueAssertion

	// ScalarPropertyValueAssertion

	// StructuredPropertyValueAssertion

	// LinkAssertion
		
	// Predicate

	// UnaryPredicate

	// EntityPredicate

	// BinaryPredicate

	// RelationPredicate

	// SameAsPredicate

	// DifferentFromPredicate

	// RelationEntityPredicate
			
}