/*
 * Copyright (C) 2010-11 Trilarion
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.iremake.common.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * We should make sure that we close everything if an error occurs.
 */
public class ResourceUtils {

    /**
     * The unique, general delimiter we use for the paths for files, archives,
     * ..
     */
    public static final String Delimiter = "/";

    /**
     * Private constructor for utility class.
     */
    private ResourceUtils() {
    }

    /**
     * Converts files to resources. Archives are read as archives with a root
     * entry. We do not want Archives as file resources, since we don't want to
     * read an archive but inside an archive.
     *
     * @param file To be converted to a Resource
     * @return A Resource instance (File, Archive, ...)
     * @throws IOException
     */
    public static Resource asResource(File file) throws IOException {
        String name = file.getName();
        if (name.endsWith(".zip") || name.endsWith(".jar")) {
            return new ArchiveResource(file, "");
        }
        return new FileResource(file);
    }

    /**
     * Given a path (not only to files but also to entries inside archives this
     * function automatically selects the right Resource implementation and
     * creates an instance. The path are all relative to the current directory.
     *
     * Directories can end without or with "/" except in archives where they
     * have to end with "/".
     *
     * Examples: "file", "folder", "folder/", "folder/file", "zipfile/folder/"
     * "folder/zipfile/zipentry" or "zipfile/folder/zipentry"
     *
     * @param path An expression delimited by "/"
     * @return The create Resource instance
     * @throws IOException
     */
    public static Resource asResource(String path) throws IOException {

        File file = null;
        String part;

        while (path != null) {
            int k = path.indexOf(Delimiter);
            if (k == -1) {
                part = path;
                path = null;
            } else {
                part = path.substring(0, k);
                path = path.substring(k + Delimiter.length());
                if (path.length() == 0) {
                    path = null;
                }
            }

            file = new File(file, part);

            // check for archives
            if (part.endsWith(".zip") || part.endsWith(".jar")) {
                return new ArchiveResource(file, path);
            }
        }

        return new FileResource(file);
    }

    /**
     * We go through the most general way, because the sub path could lead
     * inside an archive.
     *
     * @param res
     * @param subPath
     * @return
     * @throws IOException
     */
    public static Resource subResource(Resource res, String subPath) throws IOException {
        return ResourceUtils.asResource(res.getPath() + Delimiter + subPath);
    }

    /**
     * The input stream functionality of a given resource is used to obtain a
     * buffered reader, which is wanted in many applications.
     *
     * @param res Resource
     * @return Buffered reader for this resource
     * @throws IOException
     */
    public static BufferedReader getReader(Resource res) throws IOException {
        return new BufferedReader(new InputStreamReader(res.getInputStream()));
    }

    /**
     * The output stream functionality of a given resource is used to obtain a
     * buffered writer, which is wanted in many applications.
     *
     * @param res Resource
     * @return Buffered Writer for this resource
     * @throws IOException
     */
    public static BufferedWriter getWriter(Resource res) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(res.getOutputStream()));
    }

    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void copy(Resource in, Resource out) throws IOException {
        BufferedReader reader = ResourceUtils.getReader(in);
        BufferedWriter writer = ResourceUtils.getWriter(out);
        int i;
        while ((i = reader.read()) != -1) {
            writer.write(i);
        }
        reader.close();
        writer.close();
    }

    /**
     *
     * Comment: In particular empty lines are tricky.
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String readText(Resource in) throws IOException {
        StringBuilder builder = new StringBuilder(1000);
        BufferedReader reader = ResourceUtils.getReader(in);
        String str;
        while ((str = reader.readLine()) != null) {
            builder.append(str);
            if (str.length() == 0) {
                builder.append("\n\n");
            }
        }
        reader.close();
        return builder.toString();
    }
}
