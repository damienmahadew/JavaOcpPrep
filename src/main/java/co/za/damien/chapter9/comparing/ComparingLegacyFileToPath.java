package co.za.damien.chapter9.comparing;

/**
 * Legacy Method                NIO.2 Method
 * file.exists()                File.exists(path)
 * file.getName()               path.getFileName()
 * file.getAbsolutePath()       path.toAbsolutePath()
 * file.isDirectory()           Files.isDirectory(path)
 * file.isFile()                Files.isRegularFile(path)
 * file.isHidden()              Files.isHidden(path)
 * file.length()                Files.size(path)
 * file.lastModified()          Files.getLastModifiedTime(path)
 * file.setLastModified(time)   Files.setLastModifiedTime(path, fileTime)
 * file.delete()                Files.delete(path)
 * file.renameTo(otherFile)     Files.move(path, otherPath)
 * file.mkdir()                 Files.createDirectory(path)
 * file.mkdirs()                Files.createDirectories(path)
 * file.listFiles()             Files.list(path)
 */
public class ComparingLegacyFileToPath {
}
