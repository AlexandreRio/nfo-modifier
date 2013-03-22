Name: nfo-modifier
Version: 1
Release: 1
Summary: GUI editor for nfo files
BuildRoot: %{_tmppath}/%{name}-%{version}.%{release}-buildroot
Group: Applications/Editors
License: GPLv2+
URL: https://github.com/AlexandreRio/nfo-modifier
Source0: http://alexrio.fr/build/%{name}/%{name}-%{version}.tar.gz
Packager: Alexandre Rio <contact@alexrio.fr>

BuildArch: noarch

BuildRequires: java-devel >= 1:1.6.0
BuildRequires: jpackage-utils
BuildRequires: ant

Requires: java >= 1:1.6.0
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

find -name '*.class' -exec rm -f '{}' \;
find -name '*.jar' -exec rm -f '{}' \;

%build
ant jar
ant doc

%install
echo %{_prefix}

mkdir -p $RPM_BUILD_ROOT%{_javadir}
cp -p %{_builddir}/%{name}-%{version}/build/%{name}.jar $RPM_BUILD_ROOT%{_javadir}/%{name}.jar

mkdir -p $RPM_BUILD_ROOT%{_javadocdir}/%{name}
cp -rp %{_builddir}/%{name}-%{version}/doc $RPM_BUILD_ROOT%{_javadocdir}/%{name}
mkdir -p $RPM_BUILD_ROOT%{_prefix}/bin/
cat << EOT > $RPM_BUILD_ROOT%{_prefix}/bin/%{name}
#! /bin/bash
java -jar %{_javadir}/%{name}.jar
EOT
chmod ugo+x $RPM_BUILD_ROOT%{_prefix}/bin/%{name}

%files
%{_javadir}/%{name}.jar
%{_prefix}/bin/%{name}

%files javadoc
%{_javadocdir}/%{name}

%changelog
* Mon May 26 2005 Alexandre Rio <contact@alexrio.fr> 1-1
- First packaging
