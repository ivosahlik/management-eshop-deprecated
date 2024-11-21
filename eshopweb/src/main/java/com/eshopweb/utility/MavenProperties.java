package com.eshopweb.utility;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-01-17
 */
@Slf4j
@Data
public class MavenProperties {

    private String id;
    private String groupId;
    private String artifactId;
    private String version;

    public MavenProperties() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        try {
            model = reader.read(new FileReader("pom.xml"));
            this.id = model.getId();
            this.groupId = model.getGroupId();
            this.artifactId = model.getArtifactId();
            this.version = model.getVersion();
            log.info("Id: {}", model.getId());
            log.info("IGroupId: {}", model.getGroupId());
            log.info("ArtifactId: {}", model.getArtifactId());
            log.info("Version: {}", model.getVersion());
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
