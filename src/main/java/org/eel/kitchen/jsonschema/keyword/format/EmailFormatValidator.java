/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.keyword.format;

import org.codehaus.jackson.JsonNode;
import org.eel.kitchen.jsonschema.main.ValidationContext;
import org.eel.kitchen.jsonschema.main.ValidationReport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Validator for the "email" format specification.
 *
 * <p>Note: email addresses with no domain part ARE valid emails,
 * and are recognized as such. The draft does not say anywhere that the email
 * should have a domain part!</p>
 */
public final class EmailFormatValidator
    extends FormatValidator
{
    @Override
    public ValidationReport validate(final ValidationContext context,
        final JsonNode instance)
    {
        final ValidationReport report = context.createReport();

        try {
            new InternetAddress(instance.getTextValue());
        } catch (AddressException ignored) {
            report.addMessage("string is not a valid email address");
        }

        return report;
    }
}
