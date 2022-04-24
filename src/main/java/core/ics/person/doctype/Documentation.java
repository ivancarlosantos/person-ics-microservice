package core.ics.person.doctype;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

import core.ics.person.enums.Author;

@Inherited
@Documented
public @interface Documentation {

	Author author();
}
