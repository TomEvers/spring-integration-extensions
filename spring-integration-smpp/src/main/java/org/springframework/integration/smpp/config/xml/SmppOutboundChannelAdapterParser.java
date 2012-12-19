/*
   * Copyright 2002-2012 the original author or authors.
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
 */
package org.springframework.integration.smpp.config.xml;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.integration.config.xml.AbstractOutboundChannelAdapterParser;
import org.springframework.integration.config.xml.IntegrationNamespaceUtils;
import org.springframework.integration.smpp.outbound.SmppOutboundChannelAdapter;
import org.w3c.dom.Element;

/**
 * The parser for the Smpp Outbound Channel Adapter.
 *
 * @author Johanes Soetanto
 * @since 2.2
 *
 */
public class SmppOutboundChannelAdapterParser extends AbstractOutboundChannelAdapterParser {

	@Override
	protected boolean shouldGenerateIdAsFallback() {
		return true;
	}

	@Override
	protected AbstractBeanDefinition parseConsumer(Element e, ParserContext parserContext) {
		final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SmppOutboundChannelAdapter.class);
        // value attributes
        IntegrationNamespaceUtils.setValueIfAttributeDefined(builder, e, "source-address", "defaultSourceAddress");
        SmppParserUtils.setTon(e, "source-ton", "defaultSourceAddressTypeOfNumber", builder);
        // reference attributes
        SmppParserUtils.setSession(e, "smpp-session-ref", "session", "smppSession", parserContext, builder);
        IntegrationNamespaceUtils.setReferenceIfAttributeDefined(builder, e, "time-formatter", "timeFormatter");
		return builder.getBeanDefinition();
	}

}
