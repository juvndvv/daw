<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/clientes">
        <html>
            <head>
                <link rel="stylesheet" href="../styles/style.css" />
                <title>Datos</title>
            </head>
            <body>
                <h2>Datos clientes</h2>
                <table>
                    <tr bgcolor="#9acd32">
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Ciudad</th>
                        <th>Telefono</th>
                    </tr>
                    <xsl:for-each select="cliente">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="nombre" />
                            </td>
                            <td>
                                <xsl:value-of select="direccion/ciudad" />
                            </td>
                            <td>
                                <xsl:value-of select="telefono" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>