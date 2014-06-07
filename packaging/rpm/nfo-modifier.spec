Name: nfo-modifier
Version: 2.0.0
Release: 1
Summary: GUI editor for nfo files
Group: Applications/Editors
License: GPLv3
URL: https://github.com/AlexandreRio/nfo-modifier
Source0: %{name}.tar.gz
Packager: Alexandre Rio <contact@alexrio.fr>

BuildArch: noarch

BuildRequires: ant

Requires: java-headless >= 1:1.6.0
Requires: jpackage-utils

%description
Nfo and cp437 encoded files viewer and modifier.

%package javadoc
Summary: Java-docs for %{name}
Group: Documentation
Requires: jpackage-utils

%description javadoc
This package contains the API documentation for %{name}.

%prep
%setup -q


%build
ant jar
ant doc

%install
install -d -m 755 %{buildroot}%{_javadir}
install -d -m 755 %{buildroot}%{_javadocdir}/%{name}

install -p -m 644 build/%{name}.jar %{buildroot}%{_javadir}/%{name}.jar

%jpackage_script nfo.control.NfoModifier "" "" %{name} nfo-modifier true

%files
%{_javadir}/%{name}.jar
%{_prefix}/bin/%{name}

%files javadoc
%{_javadocdir}/%{name}

%changelog
* Wed Oct 02 2013 Alexandre Rio <contact@alexrio.fr> v2.0.0
- Package v2.0.0 source change versionning number
* Mon May 26 2013 Alexandre Rio <contact@alexrio.fr> v1.0
- First packaging
