/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.binding.viewattribute.provider;

import org.robobinding.binding.BindingAttributeResolver;
import org.robobinding.binding.viewattribute.AdaptedDataSetAttributes;
import org.robobinding.binding.viewattribute.DropdownLayoutAttribute;
import org.robobinding.binding.viewattribute.DropdownMappingAttribute;
import org.robobinding.binding.viewattribute.ItemLayoutAttribute;
import org.robobinding.binding.viewattribute.ItemMappingAttribute;
import org.robobinding.binding.viewattribute.OnItemClickAttribute;
import org.robobinding.binding.viewattribute.OnItemSelectedAttribute;
import org.robobinding.binding.viewattribute.SelectedItemPositionAttribute;
import org.robobinding.binding.viewattribute.SourceAttribute;

import android.widget.AbsSpinner;
import android.widget.AdapterView;


/**
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 *
 */
public class AdapterViewAttributeProvider implements BindingAttributeProvider<AdapterView<?>>
{
	private static final String SOURCE = "source";
	private static final String ITEM_LAYOUT = "itemLayout";
	private static final String ITEM_MAPPING = "itemMapping";
	private static final String DROPDOWN_LAYOUT = "dropdownLayout";
	private static final String DROPDOWN_MAPPING = "dropdownMapping";
	private static final String[] ADAPTER_ATTRIBUTE_NAMES = {SOURCE, ITEM_LAYOUT, ITEM_MAPPING, DROPDOWN_LAYOUT, DROPDOWN_MAPPING};
	
	private static final String ON_ITEM_CLICK = "onItemClick";
	private static final String ON_ITEM_SELECTED = "onItemSelected";
	private static final String SELECTED_ITEM_POSITION = "selectedItemPosition";
	
	@Override
	public void resolveSupportedBindingAttributes(AdapterView<?> adapterView, BindingAttributeResolver bindingAttributeResolver, boolean preInitializeView)
	{
		if(bindingAttributeResolver.hasOneOfAttributes(ADAPTER_ATTRIBUTE_NAMES))
		{
			AdapterViewAttributesBuilder adapterViewAttributesBuilder = new AdapterViewAttributesBuilder(preInitializeView);
			adapterViewAttributesBuilder.setSourceAttributeValue(bindingAttributeResolver.findAttributeValue(SOURCE));
			adapterViewAttributesBuilder.setItemLayoutAttributeValue(bindingAttributeResolver.findAttributeValue(ITEM_LAYOUT));
			adapterViewAttributesBuilder.setItemMappingAttributeValue(bindingAttributeResolver.findAttributeValue(ITEM_MAPPING));
			adapterViewAttributesBuilder.setDropdownLayoutAttributeValue(bindingAttributeResolver.findAttributeValue(DROPDOWN_LAYOUT));
			adapterViewAttributesBuilder.setDropdownMappingAttributeValue(bindingAttributeResolver.findAttributeValue(DROPDOWN_MAPPING));
			bindingAttributeResolver.resolveAttributes(ADAPTER_ATTRIBUTE_NAMES, adapterViewAttributesBuilder.build(adapterView));
		}
		if(bindingAttributeResolver.hasAttribute(ON_ITEM_CLICK))
		{
			String attributeValue = bindingAttributeResolver.findAttributeValue(ON_ITEM_CLICK);
			bindingAttributeResolver.resolveAttribute(ON_ITEM_CLICK, new OnItemClickAttribute(adapterView, attributeValue));
		}
		if(bindingAttributeResolver.hasAttribute(ON_ITEM_SELECTED))
		{
			String attributeValue = bindingAttributeResolver.findAttributeValue(ON_ITEM_SELECTED);
			bindingAttributeResolver.resolveAttribute(ON_ITEM_SELECTED, new OnItemSelectedAttribute(adapterView, attributeValue));
		}
		if(bindingAttributeResolver.hasAttribute(SELECTED_ITEM_POSITION))
		{
			String attributeValue = bindingAttributeResolver.findAttributeValue(SELECTED_ITEM_POSITION);
			bindingAttributeResolver.resolveAttribute(SELECTED_ITEM_POSITION, new SelectedItemPositionAttribute(adapterView, attributeValue, preInitializeView));
		}
	}
	
	public class AdapterViewAttributesBuilder
	{
		private final boolean preInitializeView;
		private String sourceAttributeValue;
		private String itemLayoutAttributeValue;
		private String itemMappingAttributeValue;
		private String dropdownLayoutAttributeValue;
		private String dropdownMappingAttributeValue;
		
		public AdapterViewAttributesBuilder(boolean preInitializeView)
		{
			this.preInitializeView = preInitializeView;
		}

		public void setSourceAttributeValue(String attributeValue)
		{
			this.sourceAttributeValue = attributeValue;
		}

		public void setItemLayoutAttributeValue(String attributeValue)
		{
			this.itemLayoutAttributeValue = attributeValue;
		}
		
		void setItemMappingAttributeValue(String itemMappingAttributeValue)
		{
			this.itemMappingAttributeValue = itemMappingAttributeValue;
		}

		public void setDropdownLayoutAttributeValue(String attributeValue)
		{
			this.dropdownLayoutAttributeValue = attributeValue;
		}
		
		void setDropdownMappingAttributeValue(String dropdownMappingAttributeValue)
		{
			this.dropdownMappingAttributeValue = dropdownMappingAttributeValue;
		}
		
		public AdaptedDataSetAttributes build(AdapterView<?> adapterView)
		{
			if (sourceAttributeValue == null || itemLayoutAttributeValue == null)
				throw new RuntimeException("When binding to an AdapterView, both source and itemLayout attributes must be provided.");

			if (adapterView instanceof AbsSpinner && dropdownLayoutAttributeValue == null)
				throw new RuntimeException("When binding to an AbsSpinner, dropdownLayout attribute must be provided.");
			
			SourceAttribute sourceAttribute = new SourceAttribute(sourceAttributeValue, preInitializeView);
			ItemLayoutAttribute itemLayoutAttribute = new ItemLayoutAttribute(itemLayoutAttributeValue, preInitializeView);
			
			DropdownLayoutAttribute dropdownLayoutAttribute = null;
			ItemMappingAttribute itemMappingAttribute = null;
			DropdownMappingAttribute dropdownMappingAttribute = null;
			
			if (dropdownLayoutAttributeValue != null)
				dropdownLayoutAttribute = new DropdownLayoutAttribute(dropdownLayoutAttributeValue, preInitializeView);
			
			if (itemMappingAttributeValue != null)
				itemMappingAttribute = new ItemMappingAttribute(itemMappingAttributeValue, preInitializeView);
			
			if (dropdownMappingAttributeValue != null)
				dropdownMappingAttribute = new DropdownMappingAttribute(dropdownMappingAttributeValue, preInitializeView);
			
			return new AdaptedDataSetAttributes(adapterView, sourceAttribute, itemLayoutAttribute, itemMappingAttribute, dropdownLayoutAttribute, dropdownMappingAttribute);
		}

	}
}
