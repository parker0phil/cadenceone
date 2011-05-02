// This code is part of camel-soap component. 
// Thanks to Tom Fennelly from Jboss Group
// 
// Copyright (C) 2009  Wilson Freitas - http://wilsondev.blogspot.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

package com.cadenceone.simplesoap.tmp;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM utility
 * <p/>
 * Thanks to Tom Fennelly from Jboss Group
 *
 * @author Wilson Freitas
 */
public class DOMUtil {


    /**
     * Count the DOM element nodes before the supplied node, having the specified
     * tag name, not including the node itself.
     * <p/>
     * Counts the sibling nodes.
     *
     * @param node    Node whose element siblings are to be counted.
     * @param tagName The tag name of the sibling elements to be counted.
     * @return The number of siblings elements before the supplied node with the
     *         specified tag name.
     */
    public static int countElementsBefore(Node node, String tagName) {
        Node parent = node.getParentNode();

        NodeList siblings = parent.getChildNodes();
        int count = 0;
        int siblingCount = siblings.getLength();

        for (int i = 0; i < siblingCount; i++) {
            Node sibling = siblings.item(i);

            if (sibling == node) {
                break;
            }
            if (sibling.getNodeType() == Node.ELEMENT_NODE && ((Element) sibling).getTagName().equals(tagName)) {
                count++;
            }
        }

        return count;
    }


    public static Node getFirstChildByType(Element element, int nodeType) {
        NodeList children = element.getChildNodes();
        int childCount = children.getLength();

        for (int i = 0; i < childCount; i++) {
            Node child = children.item(i);
            if (child.getNodeType() == nodeType) {
                return child;
            }
        }

        return null;
    }

    /**
     * Get the name from the supplied element.
     * <p/>
     * Returns the {@link Node#getLocalName() localName} of the element
     * if set (namespaced element), otherwise the
     * element's {@link Element#getTagName() tagName} is returned.
     * <p/>
     * <b>NOTE</b>: Taken from Milyn Smooks.
     *
     * @param element The element.
     * @return The element name.
     */
    public static String getName(Element element) {

        String name = element.getLocalName();

        if (name != null) {
            return name;
        } else {
            return element.getTagName();
        }
    }

}
