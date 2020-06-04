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
package io.opencaesar.oml.provider;


import io.opencaesar.oml.OmlFactory;
import io.opencaesar.oml.OmlPackage;
import io.opencaesar.oml.Vocabulary;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link io.opencaesar.oml.Vocabulary} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VocabularyItemProvider extends VocabularyBoxItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VocabularyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OmlPackage.Literals.VOCABULARY__OWNED_IMPORTS);
			childrenFeatures.add(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Vocabulary.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Vocabulary"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Vocabulary)object).getIri();
		return label == null || label.length() == 0 ?
			getString("_UI_Vocabulary_type") :
			getString("_UI_Vocabulary_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Vocabulary.class)) {
			case OmlPackage.VOCABULARY__OWNED_IMPORTS:
			case OmlPackage.VOCABULARY__OWNED_STATEMENTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_IMPORTS,
				 OmlFactory.eINSTANCE.createVocabularyExtension()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_IMPORTS,
				 OmlFactory.eINSTANCE.createVocabularyUsage()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createAspect()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createConcept()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createRelationEntity()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createStructure()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createAnnotationProperty()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createScalarProperty()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createStructuredProperty()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createFacetedScalar()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createEnumeratedScalar()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createRule()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createAspectReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createConceptReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createRelationEntityReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createStructureReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createAnnotationPropertyReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createScalarPropertyReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createStructuredPropertyReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createFacetedScalarReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createEnumeratedScalarReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createRelationReference()));

		newChildDescriptors.add
			(createChildParameter
				(OmlPackage.Literals.VOCABULARY__OWNED_STATEMENTS,
				 OmlFactory.eINSTANCE.createRuleReference()));
	}

}
